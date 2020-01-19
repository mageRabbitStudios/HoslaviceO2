package com.kinzlstanislav.hoslaviceo2.list.view

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kinzlstanislav.hoslaviceo2.base.adapter.SwipeToDeleteCallback
import com.kinzlstanislav.hoslaviceo2.base.extensions.observe
import com.kinzlstanislav.hoslaviceo2.base.extensions.showToast
import com.kinzlstanislav.hoslaviceo2.base.imageloading.GlideImageLoader
import com.kinzlstanislav.hoslaviceo2.base.view.BaseFragment
import com.kinzlstanislav.hoslaviceo2.list.R
import com.kinzlstanislav.hoslaviceo2.list.view.adapter.UsersAdapter
import com.kinzlstanislav.hoslaviceo2.list.viewmodel.ListViewModel
import com.kinzlstanislav.hoslaviceo2.list.viewmodel.ListViewModel.ListState.*
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class FragmentList : BaseFragment() {

    override val layoutResourceId = R.layout.fragment_list

    override var customBackButtonAction: (() -> Unit)? = {
        requireActivity().finishAndRemoveTask()
    }

    private val listViewModel: ListViewModel by sharedViewModel()
    private val imageLoader: GlideImageLoader by inject()

    private val usersAdapter: UsersAdapter by lazy {
        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                listViewModel.removeUserFromLocalDb(usersAdapter.usersList[viewHolder.adapterPosition])
                usersAdapter.removeAt(viewHolder.adapterPosition)
            }
        }
        ItemTouchHelper(swipeHandler).attachToRecyclerView(users_list_recycler_view)
        UsersAdapter(imageLoader)
    }

    override fun onFragmentCreated() {
        observe(listViewModel.state, Observer { state ->
            users_list_flipper.showView(when (state) {
                is UsersLoadedOffline -> users_list_recycler_view.also {
                    usersAdapter.updateItems(state.users)
                    showToast("LOL: Users loaded from offline database")
                }
                is UsersLoaded -> users_list_recycler_view.also {
                    usersAdapter.updateItems(state.users)
                    showToast("Users loaded online")
                }
                LoadingUsers -> users_list_loader
                NetworkError -> users_list_network_error
                GenericError -> users_list_generic_error
            })
        })
        users_list_recycler_view.adapter = usersAdapter
        users_swipe_to_refresh.apply {
            setOnRefreshListener {
                listViewModel.getUsers()
                isRefreshing = false
            }
        }
    }
}