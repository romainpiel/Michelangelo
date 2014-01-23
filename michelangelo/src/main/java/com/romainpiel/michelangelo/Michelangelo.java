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

    public static <T extends ViewGroup> T build(Context context, Class<T> clazz) {
        T view = null;

        InflateLayout inflateLayout = clazz.getAnnotation(InflateLayout.class);
        if (inflateLayout != null) {

            int layoutResId = inflateLayout.value();

            try {

                view = clazz.getConstructor(Context.class).newInstance(context);
                View.inflate(context, layoutResId, view);

                InjectViews injectViews = clazz.getAnnotation(InjectViews.class);
                if (injectViews != null) {

                    Injector injector = injectViews.value();
                    switch (injector) {
                        case BUTTERKNIFE:
                            injectButterKnife(view);
                            break;
                    }
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

    private static void injectButterKnife(View view) {
        final Class<?> butterknife;
        try {
            butterknife = Class.forName("butterknife.ButterKnife");
            butterknife.getMethod("inject", View.class).invoke(null, view);
        } catch (IllegalAccessException e) {
            Log.e(TAG, e.getMessage());
        } catch (InvocationTargetException e) {
            Log.e(TAG, e.getMessage());
        } catch (NoSuchMethodException e) {
            Log.e(TAG, e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "library ButterKnife not found");
        }

    }

    public static List<Method> getMethodsAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) {
        final List<Method> methods = new ArrayList<Method>();

        final List<Method> allMethods = new ArrayList<Method>(Arrays.asList(type.getDeclaredMethods()));
        for (final Method method : allMethods) {
            if (annotation == null || method.isAnnotationPresent(annotation)) {
                methods.add(method);
            }
        }

        return methods;
    }
}
