package org.paradrops.sharedelementdialog

import android.content.Context
import android.content.Intent
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
        val imageScaleType: ImageView.ScaleType,
        val tag: String,
        val customViewLayoutResId: Int,
        val customLayoutType: CustomLayoutType,
        val cancelable: Boolean
) : Parcelable {

    interface OnClickListener {
        fun onClick(viewId: Int, dialogTag: String)
    }
    var positiveButtonClickListener: OnClickListener? = null
    var negativeButtonClickListener: OnClickListener? = null
    var neutralButtonClickListener: OnClickListener? = null

    var sharedRootViewContainer: View? = null
    var sharedContentView: View? = null

    companion object {
        val ActivityRequestCode = 0
        val ActivityResultKeyDialogTag = "ActivityResultKeyDialogTag"

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
            val tag = source.readString()
            val customViewLayoutResId = source.readInt()
            val customLayoutType = CustomLayoutType.valueOf(source.readString())
            val cancelable = source.readInt() != 0
            return SharedElementDialog(title, message, neutralButtonText, negativeButtonText, positiveButtonText, imageUri, imageScaleType, tag, customViewLayoutResId, customLayoutType, cancelable)
        }
    }
    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        title.let { dest?.writeString(it) }
        message.let { dest?.writeString(it) }
        neutralButtonText.let { dest?.writeString(it) }
        negativeButtonText.let { dest?.writeString(it) }
        positiveButtonText.let { dest?.writeString(it) }
        imageUri.let { dest?.writeParcelable(it, 0) }

        dest?.writeString(imageScaleType.name)
        dest?.writeString(tag)
        dest?.writeInt(customViewLayoutResId)
        dest?.writeString(customLayoutType.name)
        if (cancelable) {
            dest?.writeInt(1)
        } else {
            dest?.writeInt(0)
        }
    }

    fun show(context: Context) {
        SharedElementDialogActivity.show(context, this, sharedRootViewContainer, sharedContentView)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode != ActivityRequestCode) {
            return
        }

        data?.extras?.getString(ActivityResultKeyDialogTag).let {
            if (it != tag) {
                return
            }
        }

        when(resultCode) {
            DialogActivityResultCode.ON_CLICK_POSITIVE_BUTTON.value -> {
                positiveButtonClickListener?.onClick(R.id.positiveButton, tag)
            }
            DialogActivityResultCode.ON_CLICK_NEGATIVE_BUTTON.value -> {
                negativeButtonClickListener?.onClick(R.id.negativeButton, tag)
            }
            DialogActivityResultCode.ON_CLICK_NEUTRAL_BUTTON.value -> {
                neutralButtonClickListener?.onClick(R.id.neutralButton, tag)
            }
            else -> {
                // Do nothing
            }
        }
    }

    class Builder() {
        private var title: String = ""
        private var message: String = ""
        private var neutralButtonText: String = ""
        private var negativeButtonText: String = ""
        private var positiveButtonText: String = ""
        private var sharedRootViewContainer: View? = null
        private var sharedContentView: View? = null
        private var imageUri: Uri = Uri.EMPTY
        private var imageScaleType: ImageView.ScaleType = ImageView.ScaleType.FIT_CENTER
        private var tag = "Default"
        private var customViewLayoutResId : Int = 0
        private var customLayoutType: CustomLayoutType = CustomLayoutType.None
        private var cancelable: Boolean = true

        private var positiveButtonClickListener: OnClickListener? = null
        private var negativeButtonClickListener: OnClickListener? = null
        private var neutralButtonClickListener: OnClickListener? = null

        fun setTitle(text: String) : Builder {
            title = text
            return this
        }

        fun setMessage(text: String) : Builder {
            message = text
            return this
        }

        fun setNeutralButton(text: String, listener: OnClickListener?) : Builder {
            neutralButtonText = text
            neutralButtonClickListener = listener
            return this
        }

        fun setNegativeButton(text: String, listener: OnClickListener?) : Builder {
            negativeButtonText = text
            negativeButtonClickListener = listener
            return this
        }

        fun setPositiveButton(text: String, listener: OnClickListener?) : Builder {
            positiveButtonText = text
            positiveButtonClickListener = listener
            return this
        }

        fun setSharedRootViewContainer(view: View) : Builder {
            sharedRootViewContainer = view
            return this
        }

        fun setSharedContentView(view: View) : Builder {
            sharedContentView = view
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

        fun setTag(text: String) : Builder {
            this.tag = text
            return this
        }

        fun setView(layoutResId: Int) : Builder {
            this.customViewLayoutResId = layoutResId
            this.customLayoutType = CustomLayoutType.ContentOnly
            return this
        }

        fun setView(layoutResId: Int, customLayoutType: CustomLayoutType) : Builder {
            this.customViewLayoutResId = layoutResId
            this.customLayoutType = customLayoutType
            return this
        }

        fun setCancelable(cancelable: Boolean) : Builder {
            this.cancelable = cancelable
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
                    imageScaleType,
                    tag,
                    customViewLayoutResId,
                    customLayoutType,
                    cancelable)
            dialog.positiveButtonClickListener = positiveButtonClickListener
            dialog.negativeButtonClickListener = negativeButtonClickListener
            dialog.neutralButtonClickListener = neutralButtonClickListener
            dialog.sharedRootViewContainer = sharedRootViewContainer
            dialog.sharedContentView = sharedContentView
            return dialog
        }
    }
}
