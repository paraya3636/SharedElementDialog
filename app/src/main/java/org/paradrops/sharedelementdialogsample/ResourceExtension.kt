package org.paradrops.sharedelementdialogsample

import android.content.ContentResolver
import android.content.res.Resources
import android.net.Uri

fun Resources.getResourceIdUri(resId: Int) : Uri {
    return Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE +
                    "://" +
                    getResourcePackageName(resId) +
                    "/" +
                    getResourceTypeName(resId) +
                    "/" +
                    getResourceEntryName(resId)
    )
}
