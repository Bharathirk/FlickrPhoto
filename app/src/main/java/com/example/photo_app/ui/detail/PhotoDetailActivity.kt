package com.example.photo_app.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.photo_app.BR
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.photo_app.R
import com.example.photo_app.base.BaseActivity
import com.example.photo_app.data.response.photo.ItemsItem
import com.example.photo_app.data.viewmodels.base.BaseViewModel
import com.example.photo_app.data.viewmodels.photoDetail.PhotoDetailViewModel
import com.example.photo_app.databinding.PhotographyDetailBinding
import kotlinx.android.synthetic.main.photography_detail.*


class PhotoDetailActivity: BaseActivity<PhotographyDetailBinding, DetailedNavigator>() {

    companion object{
        const val PHOTO_ITEM : String = "photo_item"
        fun launchActivity(context: Activity, item: ItemsItem){
            val intent = Intent(context, PhotoDetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intent.putExtra(PHOTO_ITEM, item)
            context.startActivity(intent)
        }
    }
    lateinit var photoItemResponse: ItemsItem
    private var photographyDetailBinding: PhotographyDetailBinding? = null
    private var photoDetailViewModel: PhotoDetailViewModel? = null


    override fun getLayoutId(): Int {
        return R.layout.photography_detail
    }

    override fun getViewModel(): BaseViewModel<DetailedNavigator> {
        photoDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(PhotoDetailViewModel::class.java)
        return photoDetailViewModel!!
    }

    override fun getViewBindingVarible(): Int {
        return BR.photoDetailViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photographyDetailBinding = getViewDataBinding()
        photoItemResponse = intent.extras?.get(PHOTO_ITEM) as ItemsItem
//        photographyDetailBinding?.pexelItemResponse=pexelItemResponse
        photographyDetailBinding?.executePendingBindings()
        val str = photoItemResponse.author.toString()
        val answer: String = str.substring(str.indexOf("(") + 1, str.indexOf(")"))
        photography_detail_name.text = answer
        setView(photographyDetailBinding!!,photoItemResponse)

    }

    private fun setView(binding:PhotographyDetailBinding,photoItemResponse: ItemsItem) {
        val options: RequestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
        Glide.with(this)
                .load(photoItemResponse.media!!.M)
                .apply(options)
                .into(binding.photoDetail)
    }

}
