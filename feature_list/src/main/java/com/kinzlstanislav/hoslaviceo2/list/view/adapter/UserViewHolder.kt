package com.kinzlstanislav.hoslaviceo2.list.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kinzlstanislav.hoslaviceo2.architecture.repository.model.User
import com.kinzlstanislav.hoslaviceo2.base.imageloading.GlideImageLoader
import com.kinzlstanislav.hoslaviceo2.base.viewholder.ShrinkOnTouchTileViewHolder
import com.kinzlstanislav.hoslaviceo2.base.viewholder.ShrinkOnTouchTileViewHolderImpl
import com.kinzlstanislav.hoslaviceo2.list.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*

class UserViewHolder(
    override val containerView: View,
    private val imageLoader: GlideImageLoader
) : RecyclerView.ViewHolder(containerView),
    LayoutContainer,
    ShrinkOnTouchTileViewHolder by ShrinkOnTouchTileViewHolderImpl(containerView) {

    fun bind(user: User) {
        item_user_name.text = user.name
        item_user_is_master.text = if (user.isMaster) {
            containerView.resources.getString(R.string.is_master)
        } else {
            containerView.resources.getString(R.string.is_not_master)
        }
        item_user_phone_number.text = user.phoneNumber
        imageLoader.loadFromUrl(containerView.context, user.avatarUrl, user_item_img)
    }
}