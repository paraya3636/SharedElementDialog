package org.paradrops.sharedelementdialog

import android.os.Parcel
import android.os.Parcelable

class DialogInfo(
        val title: String?,
        val message: String?,
        val neutralButtonText: String?,
        val negativeButtonText: String?,
        val positiveButtonText: String?
) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<DialogInfo> = object : Parcelable.Creator<DialogInfo> {
            override fun createFromParcel(source: Parcel): DialogInfo = DialogInfo(source)
            override fun newArray(size: Int): Array<DialogInfo?> = arrayOfNulls(size)
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
}
