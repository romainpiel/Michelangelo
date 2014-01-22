# Michelangelo

Michelangelo is a tiny android library to quickly inject a layout in your compound view.
This library is still at an early stage.

## How to use
An example of use:

```java
@InflateLayout(R.layout.custom_view)
public class MyCustomView extends FrameLayout {

    public MyCustomView(Context context) {
        super(context);
    }

    @AfterInflate
    public void showToast() {
        Toast.makeText(getContext(), "view inflated!", Toast.LENGTH_SHORT).show();
    }
}
```

`R.layout.custom_view`:
```xml
<merge xmlns:android="http://schemas.android.com/apk/res/android">

    <TextView
            android:id="@+id/my_text_view"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>

</merge>
```

Inflating that View is pretty straightforward:
```java
MyCustomView view = Michelangelo.inflate(this, MyCustomView.class, new OnViewChangedListener<MyCustomView>() {
    @Override
    public void onViewChanged(MyCustomView view) {
        ((TextView) view.findViewById(R.id.my_text_view)).setText("hey!");
    }
});
```

## Annotations description

Use the annotation `@InflateLayout` on your `ViewGroup` and specify the layout to inflate. You'd better use a `<merge>` root on your layout for better performances.

Use the annotation `@AfterInflate` on your compound view's methods you want to run straight after the layout is inflated with Michelangelo.

## Michelangelo and ButterKnife

Michelangelo plays very well with Jake Wharton's library [ButterKnife](https://github.com/JakeWharton/butterknife).
After adding the library to your project, just add your injections to your compound views as usual:

```java
@InjectView(R.id.my_text_view) TextView myTextView;
```

Then call this method to inflate the layout + inject the views:
```java
Michelangelo.inflateAndInject(this, MyCustomView.class);
```

Then the injected views are ready to be used! See the [example](https://github.com/RomainPiel/Michelangelo/blob/master/michelangelo-sample/src/main/java/com/romainpiel/michelangelo/sample/MainActivity.java).

## License
```
Copyright 2014 Romain Piel

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```