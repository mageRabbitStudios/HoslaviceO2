package com.kinzlstanislav.hoslaviceo2.view

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.kinzlstanislav.hoslaviceo2.R
import com.kinzlstanislav.hoslaviceo2.list.viewmodel.ListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val listViewModel: ListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchDataOnAppStart()
    }

    // Sometimes it is preferable to load data on the start of the application so
    // there is no need to wait at further places in the app for the data to fetch.
    private fun fetchDataOnAppStart() {
        listViewModel.getMembers()
    }
}