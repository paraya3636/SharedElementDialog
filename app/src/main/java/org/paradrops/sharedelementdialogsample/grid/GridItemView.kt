package org.paradrops.sharedelementdialogsample.grid

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import org.paradrops.sharedelementdialogsample.R

class GridItemView : ImageView {
    companion object {
        fun inflate(context: Context) : GridItemView {
            return LayoutInflater.from(context).inflate(R.layout.view_grid_item, null) as GridItemView
        }
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr)
}
