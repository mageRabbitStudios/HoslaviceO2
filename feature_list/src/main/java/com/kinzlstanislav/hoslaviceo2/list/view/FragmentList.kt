package com.kinzlstanislav.hoslaviceo2.list.view

import androidx.lifecycle.Observer
import com.kinzlstanislav.hoslaviceo2.base.extensions.observe
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

    private val listViewModel: ListViewModel by sharedViewModel()
    private val imageLoader: GlideImageLoader by inject()

    private val usersAdapter: UsersAdapter by lazy { UsersAdapter(imageLoader) }

    override fun onFragmentCreated() {
        observe(listViewModel.state, Observer { state ->
            users_list_flipper.showView(when (state) {
                is UsersLoaded -> users_list_recycler_view.also { usersAdapter.updateItems(state.users) }
                LoadingUsers -> users_list_loader
                NetworkError -> users_list_network_error
                GenericError -> users_list_generic_error
            })
        })
        users_list_recycler_view.adapter = usersAdapter
    }
}