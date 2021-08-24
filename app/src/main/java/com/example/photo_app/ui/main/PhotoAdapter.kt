package com.example.photo_app.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.photo_app.R
import com.example.photo_app.data.response.photo.ItemsItem
import com.example.photo_app.databinding.PhotographyItemBinding

class PhotoAdapter(val context: Context, clickManager: ClickManager):RecyclerView.Adapter<PhotoAdapter.ChatHolder>() {
    private var photoResponseItem: List<ItemsItem>? = null
    private var clickManager: ClickManager? = clickManager

    init {
        photoResponseItem = ArrayList()
    }

    interface ClickManager {
        fun onItemViewClick(pexelResponseItem: ItemsItem)
    }

    fun setCourseAdapter(item: List<ItemsItem>) {
        if (item == null) {
            return
        }
        photoResponseItem = item

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        return ChatHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.photography_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        val item: ItemsItem = photoResponseItem?.get(position)!!
        val binding: PhotographyItemBinding = holder.getBinding()
        setImage(binding, item)
        val str = item.author.toString()
        val answer: String = str.substring(str.indexOf("(") + 1, str.indexOf(")"))
        binding.photographyName.text = answer
        binding.photoResponseItem = item
    }

    override fun getItemCount(): Int {
        return photoResponseItem!!.size
    }

    inner class ChatHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener {
        private var binding: PhotographyItemBinding? = null
        init {
            binding = DataBindingUtil.bind(itemView)
            itemView.setOnClickListener(this)
        }

        fun getBinding(): PhotographyItemBinding {
            return binding!!
        }

        override fun onClick(v: View?) {
            clickManager!!.onItemViewClick(photoResponseItem!!.get(adapterPosition))
        }

    }

    private fun setImage(binding: PhotographyItemBinding, item: ItemsItem) {
        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
        Glide.with(context)
            .load(item.media!!.M)
            .apply(options)
            .into(binding.photoImage)
    }

}