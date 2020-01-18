package com.kinzlstanislav.hoslaviceo2.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kinzlstanislav.hoslaviceo2.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchDataOnAppStart()
    }

    // Sometimes it is preferable to load data on the start of the application so
    // there is no need to wait at further places in the app for the data to fetch.
    private fun fetchDataOnAppStart() {
//        contributorsListViewModel.getRubyContributors()
    }
}