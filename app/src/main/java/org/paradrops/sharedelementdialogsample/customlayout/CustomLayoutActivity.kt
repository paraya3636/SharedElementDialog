package org.paradrops.sharedelementdialogsample.customlayout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.Toast
import org.paradrops.sharedelementdialog.CustomLayoutType
import org.paradrops.sharedelementdialog.SharedElementDialog
import org.paradrops.sharedelementdialogsample.R
import org.paradrops.sharedelementdialogsample.getResourceIdUri

class CustomLayoutActivity : AppCompatActivity() {
    companion object {
        fun navigateIntent(context: Context) : Intent = Intent(context, CustomLayoutActivity::class.java)
    }

    private val customButton by lazy { findViewById<ImageButton>(R.id.customButton) }
    private val fullCustomButton by lazy { findViewById<ImageButton>(R.id.fullCustomButton) }

    private val contentCustomDialog by lazy {
        val listener = object : SharedElementDialog.OnClickListener {
            override fun onClick(viewId: Int, dialogTag: String) {
                when(viewId) {
                    R.id.positiveButton -> {
                        Toast.makeText(this@CustomLayoutActivity, "Content custom dialog Positive Button", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        SharedElementDialog.Builder()
                .setTitle("Title")
                .setMessage("Custom Message Layout")
                .setImageUri(resources.getResourceIdUri(R.drawable.cat01))
                .setPositiveButton("OK", listener)
                .setSharedContentView(customButton)
                .setSharedRootViewContainer(customButton)
                .setView(R.layout.view_content_custom_dialog)
                .setTag("ContentCustomDialog")
                .create()
    }

    private val fullCustomDialog by lazy {
        val listener = object : SharedElementDialog.OnClickListener {
            override fun onClick(viewId: Int, dialogTag: String) {
                when(viewId) {
                    R.id.positiveButton -> {
                        Toast.makeText(this@CustomLayoutActivity, "Full custom dialog Positive Button", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        SharedElementDialog.Builder()
                .setTitle("Title")
                .setMessage("Full custom Message Layout")
                .setImageUri(resources.getResourceIdUri(R.drawable.cat02))
                .setPositiveButton("OK", listener)
                .setSharedContentView(fullCustomButton)
                .setSharedRootViewContainer(fullCustomButton)
                .setView(R.layout.view_full_custom_dialog, CustomLayoutType.Full)
                .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_layout)

        customButton.setOnClickListener {
            contentCustomDialog.show(this)
        }

        fullCustomButton.setOnClickListener {
            fullCustomDialog.show(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        contentCustomDialog.onActivityResult(requestCode, resultCode, data)
        fullCustomDialog.onActivityResult(requestCode, resultCode, data)
    }
}
