package org.paradrops.sharedelementdialogsample.list

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import org.paradrops.sharedelementdialogsample.R

class ListItemView : LinearLayout {
    companion object {
        fun inflate(context: Context) : ListItemView {
            return LayoutInflater.from(context).inflate(R.layout.view_list_item, null) as ListItemView
        }
    }

    val image by lazy { findViewById<ImageView>(R.id.image) }
    val title by lazy { findViewById<TextView>(R.id.title) }
    val message by lazy { findViewById<TextView>(R.id.message) }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr)
}
