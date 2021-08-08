package com.example.mynewyoutube.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewyoutube.R
import com.example.mynewyoutube.databinding.RvItemBinding
import com.example.mynewyoutube.models.Item
import com.squareup.picasso.Picasso

class VideoRvAdapter(var itemOnClick: itemClick) :
    ListAdapter<Item, VideoRvAdapter.Vh>(MyDiffUtill()) {
    inner class Vh(var item: RvItemBinding) : RecyclerView.ViewHolder(item.root) {
        fun onBind(data: Item,position: Int) {

            Picasso.get().load(data.snippet.thumbnails.high.url)
                .placeholder(R.drawable.loading_thumbnail)
                .error(R.drawable.no_thumbnail)
                .into(item.itemVideoImage)


            item.videoName.text = data.snippet.title
            item.videoDesc.text = data.snippet.description

            item.itemVideoImage.setOnClickListener {
                itemOnClick.itemOnClick(data,position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position),position)
    }

    class MyDiffUtill : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }

    interface itemClick {
        fun itemOnClick(data: Item,position: Int)
    }

}