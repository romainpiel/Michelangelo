# Michelangelo

Michelangelo is a tiny android library to quickly inject a layout in your compound view.
This library is still at an early stage.

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