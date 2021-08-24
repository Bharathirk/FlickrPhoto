package com.example.photo_app.data.viewmodels.photo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.photo_app.data.response.common.AppResponse
import com.example.photo_app.data.viewmodels.base.BaseViewModel
import com.example.photo_app.ui.main.MainNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhotoViewModel @Inject constructor() : BaseViewModel<MainNavigator>() {

    fun getPhoto(): MutableLiveData<AppResponse<Any>> {
        val responseBody = MutableLiveData<AppResponse<Any>>()
    viewModelScope.launch(Dispatchers.IO){
        val response = api.getPexelList("json","dogs","1")
        responseBody.postValue(AppResponse.success(response))
    }

        return responseBody
    }



}