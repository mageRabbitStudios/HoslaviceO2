package com.kinzlstanislav.hoslaviceo2.list.view

import androidx.lifecycle.Observer
import com.kinzlstanislav.hoslaviceo2.base.extensions.observe
import com.kinzlstanislav.hoslaviceo2.base.view.BaseFragment
import com.kinzlstanislav.hoslaviceo2.list.R
import com.kinzlstanislav.hoslaviceo2.list.viewmodel.ListViewModel
import com.kinzlstanislav.hoslaviceo2.list.viewmodel.ListViewModel.ListState.*
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class FragmentList : BaseFragment() {

    override val layoutResourceId = R.layout.fragment_list

    private val listViewModel: ListViewModel by sharedViewModel()

    override fun onFragmentCreated() {
        observe(listViewModel.state, Observer { state ->
            users_list_flipper.showView(when (state) {
                is UsersLoaded -> users_list_recycler_view.also {
                    println(state.users)
                    // update adapter
                }
                LoadingUsers -> users_list_loader
                NetworkError -> users_list_network_error
                GenericError -> users_list_generic_error
            })
        })
    }
}