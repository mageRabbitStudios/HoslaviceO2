package com.kinzlstanislav.hoslaviceo2.list.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kinzlstanislav.hoslaviceo2.architecture.repository.model.User
import com.kinzlstanislav.hoslaviceo2.base.extensions.inflateViewHolder
import com.kinzlstanislav.hoslaviceo2.base.imageloading.GlideImageLoader
import com.kinzlstanislav.hoslaviceo2.list.R

class UsersAdapter(private val imageLoader: GlideImageLoader)
    : RecyclerView.Adapter<UserViewHolder>() {

    private val _usersList: MutableList<User> = mutableListOf()
    val usersList: List<User> = _usersList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        parent.inflateViewHolder(R.layout.item_user), imageLoader
    )

    override fun getItemCount() = usersList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(usersList[position])
    }

    fun updateItems(users: List<User>) {
        _usersList.apply {
            clear()
            addAll(users)
        }
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        _usersList.removeAt(position)
        notifyItemRemoved(position)
    }
}