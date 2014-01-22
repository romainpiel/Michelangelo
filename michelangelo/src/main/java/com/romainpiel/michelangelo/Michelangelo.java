package com.romainpiel.michelangelo;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Michelangelo
 * romainpiel
 * 21/01/2014
 */
public class Michelangelo {

    private static final String TAG = "MICHELANGELO";

    public static <T extends ViewGroup> T inflate(Context context, Class<T> clazz, OnViewChangedListener<T> listener) {
        T view = null;

        InflateLayout inflateLayout = clazz.getAnnotation(InflateLayout.class);
        if (inflateLayout != null) {

            int layoutResId = inflateLayout.value();

            try {

                view = clazz.getConstructor(Context.class).newInstance(context);
                View.inflate(context, layoutResId, view);

                if (listener != null) {
                    listener.onViewChanged(view);
                }

                List<Method> afterInflateMethods = getMethodsAnnotatedWith(clazz, AfterInflate.class);
                for (Method method : afterInflateMethods) {
                    method.invoke(view);
                }

            } catch (InstantiationException e) {
                Log.e(TAG, e.getMessage());
            } catch (IllegalAccessException e) {
                Log.e(TAG, e.getMessage());
            } catch (InvocationTargetException e) {
                Log.e(TAG, e.getMessage());
            } catch (NoSuchMethodException e) {
                Log.e(TAG, e.getMessage());
            }
        }

        return view;
    }

    public static <T extends ViewGroup> T inflate(Context context, Class<T> clazz) {
        return inflate(context, clazz, null);
    }

    public static <T extends ViewGroup> T inflateAndInject(Context context, Class<T> clazz) {
        final Class<?> butterknife;
        try {
            butterknife = Class.forName("butterknife.ButterKnife");
            return inflate(context, clazz, new OnViewChangedListener<T>() {
                @Override
                public void onViewChanged(T view) {
                    try {
                        butterknife.getMethod("inject", View.class).invoke(null, view);
                    } catch (IllegalAccessException e) {
                        Log.e(TAG, e.getMessage());
                    } catch (InvocationTargetException e) {
                        Log.e(TAG, e.getMessage());
                    } catch (NoSuchMethodException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            });
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "library ButterKnife not found");
        }

        return null;
    }

    public static List<Method> getMethodsAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) {
        final List<Method> methods = new ArrayList<Method>();
        Class<?> clazz = type;
        while (clazz != Object.class) {

            final List<Method> allMethods = new ArrayList<Method>(Arrays.asList(clazz.getDeclaredMethods()));
            for (final Method method : allMethods) {
                if (annotation == null || method.isAnnotationPresent(annotation)) {
                    methods.add(method);
                }
            }

            clazz = clazz.getSuperclass();
        }
        return methods;
    }
}
