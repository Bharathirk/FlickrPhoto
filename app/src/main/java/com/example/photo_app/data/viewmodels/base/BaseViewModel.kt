package com.example.photo_app.data.viewmodels.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photo_app.data.api.AppApi
import com.example.photo_app.data.response.common.AppResponse
import java.lang.ref.WeakReference
import javax.inject.Inject

abstract class BaseViewModel<N> : ViewModel() {

    @Inject
    lateinit var api: AppApi

    private lateinit var mNavigator: WeakReference<N>

    var navigator: N
        get() = mNavigator.get()!!
        set(navigator) {
            this.mNavigator = WeakReference(navigator)
        }

    val response = MutableLiveData<AppResponse<Any>>()

    val loadingStatus = MutableLiveData<Boolean>()


}