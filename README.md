# Michelangelo

Layout inflation library for Android which uses annotation processing to write the code you don't want to write and simplify your compound views.

## How to use

An example of use:

```java
@InflateLayout(R.layout.custom_view)
public class MyCustomView extends FrameLayout {

    public MyCustomView(Context context) {
        super(context);
    }

    @AfterInflate
    public void updateTextView() {
        ((TextView) findViewById(R.id.my_text_view)).setText("hey!");
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
MyCustomView view = Michelangelo.build(this, MyCustomView.class);
```

## Post inflate methods

Use the annotation `@AfterInflate` on your compound view's methods you want to run straight after the layout is inflated with Michelangelo.

## Michelangelo and ButterKnife

Michelangelo plays very well with Jake Wharton's library [ButterKnife](https://github.com/JakeWharton/butterknife). Just add the annotation `@InjectViews` to your `ViewGroup` and Michelangelo will do the rest.

Example using ButterKnife:

```java
@InflateLayout(R.layout.item_painting)
@InjectViews
public class PaintingItemView extends LinearLayout {

    @InjectView(R.id.image) ImageView image;
    @InjectView(R.id.title) TextView title;

    public PaintingItemView(Context context) {
        super(context);
        setOrientation(HORIZONTAL);
    }

    public void bind(Painting painting) {
        image.setImageResource(painting.getDrawableResId());
        title.setText(painting.getTitle());
    }
}
```

## Order of events

When you call `Michelangelo.build()`:

1. The view gets inflated
2. If specified, the injector is applied (`ButterKnife.inject(view)`)
3. If exist, methods annotated with `@AfterInflate` are run.

## Sample

See the [sample](https://github.com/RomainPiel/Michelangelo/tree/master/michelangelo-sample/src/main) for a common use of this library with `ListView` adapters.

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