package org.paradrops.sharedelementdialog

import android.content.Context
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.ImageView

class SharedElementDialog(
        val title: String,
        val message: String,
        val neutralButtonText: String,
        val negativeButtonText: String,
        val positiveButtonText: String,
        val imageUri: Uri,
        val imageScaleType: ImageView.ScaleType
) : Parcelable {

    var sharedRootView: View? = null
    var sharedChildView: View? = null

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<SharedElementDialog> = object : Parcelable.Creator<SharedElementDialog> {
            override fun createFromParcel(source: Parcel): SharedElementDialog = create(source)
            override fun newArray(size: Int): Array<SharedElementDialog?> = arrayOfNulls(size)
        }

        private fun create(source: Parcel) : SharedElementDialog {
            val title = source.readString()
            val message = source.readString()
            val neutralButtonText = source.readString()
            val negativeButtonText = source.readString()
            val positiveButtonText = source.readString()
            val imageUri = source.readParcelable<Uri>(Uri::class.java.classLoader)
            val imageScaleType = ImageView.ScaleType.valueOf(source.readString())
            return SharedElementDialog(title, message, neutralButtonText, negativeButtonText, positiveButtonText, imageUri, imageScaleType)
        }
    }
    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        title?.let { dest?.writeString(it) }
        message?.let { dest?.writeString(it) }
        neutralButtonText?.let { dest?.writeString(it) }
        negativeButtonText?.let { dest?.writeString(it) }
        positiveButtonText?.let { dest?.writeString(it) }
        imageUri?.let { dest?.writeParcelable(it, 0) }

        val name = imageScaleType.name
        dest?.writeString(name)
    }

    fun show(context: Context) {
        SharedElementDialogActivity.show(context, this, sharedRootView, sharedChildView)
    }

    class Builder() {
        private var title: String = ""
        private var message: String = ""
        private var neutralButtonText: String = ""
        private var negativeButtonText: String = ""
        private var positiveButtonText: String = ""
        private var sharedRootView: View? = null
        private var sharedChildView: View? = null
        private var imageUri: Uri = Uri.EMPTY
        private var imageScaleType: ImageView.ScaleType = ImageView.ScaleType.FIT_CENTER

        fun setTitle(text: String) : Builder {
            title = text
            return this
        }

        fun setMessage(text: String) : Builder {
            message = text
            return this
        }

        fun setNeutralButton(text: String) : Builder {
            neutralButtonText = text
            return this
        }

        fun setNegativeButton(text: String) : Builder {
            negativeButtonText = text
            return this
        }

        fun setPositiveButton(text: String) : Builder {
            positiveButtonText = text
            return this
        }

        fun setSharedRootView(view: View) : Builder {
            sharedRootView = view
            return this
        }

        fun setSharedChildView(view: View) : Builder {
            sharedChildView = view
            return this
        }

        fun setImageUri(uri: Uri) : Builder {
            imageUri = uri
            return this
        }

        fun setImageScaleType(scaleType: ImageView.ScaleType) : Builder {
            imageScaleType = scaleType
            return this
        }

        fun create() : SharedElementDialog {
            val dialog = SharedElementDialog(
                    title,
                    message,
                    neutralButtonText,
                    negativeButtonText,
                    positiveButtonText,
                    imageUri,
                    imageScaleType)
            dialog.sharedRootView = sharedRootView
            dialog.sharedChildView = sharedChildView
            return dialog
        }
    }
}
