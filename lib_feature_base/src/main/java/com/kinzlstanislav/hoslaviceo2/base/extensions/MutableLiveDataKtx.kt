package com.kinzlstanislav.hoslaviceo2.base.extensions

import androidx.lifecycle.MutableLiveData

fun MutableLiveData<*>.set(state: Any) {
    value = state
}