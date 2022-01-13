package com.example.apprenticeassignment2.services

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apprenticeassignment2.R
import com.example.apprenticeassignment2.data.Photo
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_image_recycler.*


class MainAdapter(var items: MutableList<Photo>, var onImageClickListener: OnImageClick) :
    RecyclerView.Adapter<MainAdapter.PostHolder>() {

    override fun getItemCount() = items.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_image_recycler, parent, false)
    )

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bind(items[position], position)
    }

    //set new list of photos and notifies the change
    fun setList(newList: MutableList<Photo>) {
        items = newList
        notifyDataSetChanged()
    }

    // addphotos to existing and notify adapter with the new photos added
    fun addPhotos(newList: List<Photo>) {
        val lastIndex = items.size - 1
        items.addAll(newList)
        notifyItemRangeInserted(lastIndex, newList.size)
    }

    inner class PostHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(item: Photo, position: Int) {
            // Final photo url construction using id, server and secret
            val imageUrl: String = "https://live.staticflickr.com/${item.serverID}/${item.id}_${item.secretId}.jpg"
            Glide.with(itemView)
                .load(imageUrl)
                .into(photo_image)
            title_text.text = item.title

            itemView.setOnClickListener {
                onImageClickListener.onClick(imageUrl)
            }
        }

    }

    //handle on image click
    interface OnImageClick {
        fun onClick(imageUrl: String)
    }
}