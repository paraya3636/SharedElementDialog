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

class ClickEventActivity : AppCompatActivity(), SharedElementDialog.OnClickListener {
    companion object {
        fun navigateIntent(context: Context) : Intent = Intent(context, ClickEventActivity::class.java)
    }

    private val cardView by lazy { findViewById<CardView>(R.id.cardView) }
    private val image by lazy { findViewById<ImageView>(R.id.image) }

    private val dialog by lazy {
        SharedElementDialog.Builder()
                .setPositiveButton("OK", this)
                .setNegativeButton("CANCEL", this)
                .setNeutralButton("♥", this)
                .setImageUri(resources.getResourceIdUri(R.drawable.cat03))
                .setSharedRootViewContainer(cardView)
                .setSharedContentView(image)
                .setCancelable(false)
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
