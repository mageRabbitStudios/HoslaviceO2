package com.kinzlstanislav.hoslaviceo2.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kinzlstanislav.hoslaviceo2.R
import com.kinzlstanislav.hoslaviceo2.list.viewmodel.ListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}