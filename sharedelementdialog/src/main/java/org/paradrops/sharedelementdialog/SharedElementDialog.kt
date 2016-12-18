package org.paradrops.sharedelementdialog

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.View

class SharedElementDialog(
        val title: String?,
        val message: String?,
        val neutralButtonText: String?,
        val negativeButtonText: String?,
        val positiveButtonText: String?
) : Parcelable {

    var sharedRootView: View? = null
    var sharedChildView: View? = null

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<SharedElementDialog> = object : Parcelable.Creator<SharedElementDialog> {
            override fun createFromParcel(source: Parcel): SharedElementDialog = SharedElementDialog(source)
            override fun newArray(size: Int): Array<SharedElementDialog?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readString(), source.readString(), source.readString(), source.readString(), source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        title?.let { dest?.writeString(it) }
        message?.let { dest?.writeString(it) }
        neutralButtonText?.let { dest?.writeString(it) }
        negativeButtonText?.let { dest?.writeString(it) }
        positiveButtonText?.let { dest?.writeString(it) }
    }

    fun show(context: Context) {
        SharedElementDialogActivity.show(context, this, sharedRootView, sharedChildView)
    }

    class Builder() {
        private var title: String? = null
        private var message: String? = null
        private var neutralButtonText: String? = null
        private var negativeButtonText: String? = null
        private var positiveButtonText: String? = null
        private var sharedRootView: View? = null
        private var sharedChildView: View? = null

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

        fun create() : SharedElementDialog {
            val dialog = SharedElementDialog(
                    title,
                    message,
                    neutralButtonText,
                    negativeButtonText,
                    positiveButtonText)
            dialog.sharedRootView = sharedRootView
            dialog.sharedChildView = sharedChildView
            return dialog
        }
    }
}
