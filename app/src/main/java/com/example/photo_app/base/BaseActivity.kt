package com.example.photo_app.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.photo_app.R
import com.example.photo_app.data.viewmodels.base.BaseViewModel
import com.example.photo_app.databinding.ActivityBaseBinding
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.progress_bar.view.*
import javax.inject.Inject


abstract class BaseActivity<VB : ViewDataBinding, V : Any>: DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var dialog: AlertDialog? = null
    private var viewBinding: VB? = null
    private var viewModel: BaseViewModel<V>? = null
    private var baseBinding: ActivityBaseBinding?=null


    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): BaseViewModel<V>

    abstract fun getViewBindingVarible(): Int

    fun getViewDataBinding(): VB {
        return viewBinding!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)
        setDataBinding()
        observeLoadingStatus()

    }

    private fun setDataBinding() {
        viewBinding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), baseBinding!!.frameContent, true)
        this.viewModel = getViewModel()
        viewBinding!!.setVariable(getViewBindingVarible(), viewModel)
        viewBinding!!.executePendingBindings()
    }

    fun setHeaderTitle(title: String) {
        if (supportActionBar != null) supportActionBar!!.title = title
    }


    fun showToast(input: String) {
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show()
    }

    fun showLoading() {
        baseBinding!!.includProgress.progress_bar.visibility = View.VISIBLE
    }

    fun hideLoading() {

        baseBinding!!.includProgress.progress_bar.visibility = View.INVISIBLE
    }

    fun observeLoadingStatus() {
        viewModel!!.loadingStatus.observe(this, Observer { isLoading ->
            if (!isLoading!!) {
                hideLoading()
            } else {
                showLoading()
            }
        })
    }

}