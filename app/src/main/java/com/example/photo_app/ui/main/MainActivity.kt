package com.example.photo_app.ui.main
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.photo_app.R
import com.example.photo_app.BR
import com.example.photo_app.base.BaseActivity
import com.example.photo_app.data.response.photo.ItemsItem
import com.example.photo_app.data.response.photo.PhotoResponse
import com.example.photo_app.data.viewmodels.base.BaseViewModel
import com.example.photo_app.data.viewmodels.photo.PhotoViewModel
import com.example.photo_app.databinding.ActivityMainBinding
import com.example.photo_app.ui.detail.PhotoDetailActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, MainNavigator>(), PhotoAdapter.ClickManager  {
    private var activityMainBinding: ActivityMainBinding? = null
    private var photoViewModel: PhotoViewModel? = null
    private var photoAdapter:PhotoAdapter?= null
    var PhotoResponseList:ArrayList<ItemsItem> = ArrayList<ItemsItem>()


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): BaseViewModel<MainNavigator> {
        photoViewModel =
            ViewModelProvider(this, viewModelFactory).get(PhotoViewModel::class.java)
        return photoViewModel!!
    }

    override fun getViewBindingVarible(): Int {
        return BR.photoViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = getViewDataBinding()

        photoAdapter = PhotoAdapter(this,this)
        activityMainBinding?.photoAdapter = photoAdapter
        getPhotoImages()

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                filterTag(p0.toString())
            }
        })
    }
    //getApiList
    private fun getPhotoImages() {
        photoViewModel!!.getPhoto().observe(this, { response ->
            if (response?.data != null) {
                val photoResponse = response.data as PhotoResponse
                if (photoResponse.items!!.size>0){
                    PhotoResponseList.addAll(photoResponse.items as List<ItemsItem>)
                    setView(PhotoResponseList)
                }

            } else {
                showToast(response.throwable?.message!!)
            }
        })
    }
    //EnterTagsName
    private fun filterTag(skey: String) {
        PhotoResponseList.clear()
        photoViewModel!!.getTagPhoto(skey).observe(this, { response ->
            if (response?.data != null) {
                val photoResponse = response.data as PhotoResponse
                if (photoResponse.items!!.size>0){
                    PhotoResponseList.addAll(photoResponse.items as List<ItemsItem>)
                    setView(PhotoResponseList)
                }

            } else {
                showToast(response.throwable?.message!!)
            }
        })
    }
    //SetAdapterValue
    private fun setView(photoResponseItem: List<ItemsItem>) {
        if (photoResponseItem != null && photoResponseItem.size > 0) {
            activityMainBinding!!.executePendingBindings()
            photoAdapter!!.setCourseAdapter(photoResponseItem)
        }
    }
    override fun onItemViewClick(photoResponseItem: ItemsItem) {
        PhotoDetailActivity.launchActivity(this,photoResponseItem)
    }
}