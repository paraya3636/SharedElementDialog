[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Version](https://jitpack.io/v/paraya3636/SharedElementDialog.svg)](https://jitpack.io/#paraya3636/SharedElementDialog)

# SharedElementDialog

SharedElementDialog is MaterialDesign SharedElementTransition library for Android.  
This library is Android AlertDialog like and easy to use.

![Demo](art/sample.gif)

## How do I use it?

### Setup
```
app gradle file

dependencies {
    compile 'com.github.paraya3636:SharedElementDialog:0.4'
}
repositories {
    maven { url 'https://jitpack.io' }
}
```

### Sample code
[Show detail sample code.](https://github.com/paraya3636/SharedElementDialog/tree/master/app/src/main/java/org/paradrops/sharedelementdialogsample)

```kotlin
Kotlin

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_grid)
    
    SharedElementDialog.Builder()
            .setTitle("Title")
            .setMessage("Message")
            .setPositiveButton("OK", null)
            .setImageUri(uri)
            .setSharedContentView(imageView) // Transition content to dialog content. For example, ImageView 
            .setSharedRootViewContainer(imageView) // Transition content to dialog root view. Same as the content.
            .create()
            .show(this)
}
```

### Click event

SharedElementDialog is Activity. If you use click event, onActivityResult（） arguments to dialog.

```kotlin
class ClickEventActivity : AppCompatActivity(), SharedElementDialog.OnClickListener {
    
    private val image by lazy { findViewById(R.id.image) as ImageView }

    private val dialog by lazy {
        SharedElementDialog.Builder()
                .setPositiveButton("OK", this)
                .setNegativeButton("CANCEL", this)
                .setNeutralButton("♥", this)
                .setImageUri(uri)
                .setSharedRootViewContainer(image)
                .setSharedContentView(image)
                .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_event)

        image.setOnClickListener {
            dialog.show(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        dialog.onActivityResult(requestCode, resultCode, data)
    }

    override fun onClick(viewId: Int, dialogTag: String) {
        when(viewId) {
            R.id.positiveButton -> {
                Toast.makeText(this@ClickEventActivity, "Positive Button!", Toast.LENGTH_SHORT).show()
            }
            R.id.negativeButton -> {
                Toast.makeText(this@ClickEventActivity, "Negative Button!", Toast.LENGTH_SHORT).show()
            }
            R.id.neutralButton -> {
                Toast.makeText(this@ClickEventActivity, "Neutral Button!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
```

# SDK Version
Require  
minSdkVersion:21 Android5+  

Build  
minSdkVersion:15 Android4.0.3+ (Don't animation)

# License

    Copyright 2016 paraya3636

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
