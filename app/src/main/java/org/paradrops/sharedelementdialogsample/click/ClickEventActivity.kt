package org.paradrops.sharedelementdialogsample.click

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.widget.ImageView
import android.widget.Toast
import org.paradrops.sharedelementdialog.SharedElementDialog
import org.paradrops.sharedelementdialogsample.R
import org.paradrops.sharedelementdialogsample.getResourceIdUri

class ClickEventActivity : AppCompatActivity() {

    companion object {
        fun navigateIntent(context: Context) : Intent = Intent(context, ClickEventActivity::class.java)
    }

    private val cardView by lazy { findViewById(R.id.cardView) as CardView }
    private val image by lazy { findViewById(R.id.image) as ImageView }

    private val dialog by lazy {
        SharedElementDialog.Builder()
                .setPositiveButton("OK")
                .setNegativeButton("CANCEL")
                .setNeutralButton("♥")
                .setImageUri(resources.getResourceIdUri(R.drawable.cat03))
                .setSharedRootViewContainer(cardView)
                .setSharedContentView(image)
                .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click_event)

        cardView.setOnClickListener {
            dialog.show(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        dialog.onActivityResult(requestCode, resultCode, data, object : SharedElementDialog.SharedElementDialogCallback {
            override fun onClickPositiveButton() {
                Toast.makeText(this@ClickEventActivity, "Positive Button!", Toast.LENGTH_SHORT).show()
            }

            override fun onClickNegativeButton() {
                Toast.makeText(this@ClickEventActivity, "Negative Button!", Toast.LENGTH_SHORT).show()
            }

            override fun onClickNeutralButton() {
                Toast.makeText(this@ClickEventActivity, "Neutral Button!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
