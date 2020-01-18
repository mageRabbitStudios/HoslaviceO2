package com.kinzlstanislav.hoslaviceo2.base.viewholder

interface ShrinkOnTouchTileViewHolder {

    var touchUpAction: () -> Unit

    fun shrink()

    fun shrinkBack()
}
