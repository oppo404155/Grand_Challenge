package com.example.grandchallenge.feature1.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grandchallenge.databinding.AlbumItmeBinding
import com.example.grandchallenge.feature1.domain.models.Album

class UserInfoAdapter : RecyclerView.Adapter<UserInfoAdapter.UserInfoVH>() {
    private val albumList = ArrayList<Album>()
    var onItemClicked:((Album)->Unit)?=null

    inner class UserInfoVH(private val binding: AlbumItmeBinding) :
        RecyclerView.ViewHolder(binding.root) {
       init {
           itemView.setOnClickListener {
               onItemClicked?.invoke(albumList[adapterPosition])
           }
       }
        fun bind(album: Album) {
            binding.apply {
                albumTxv.text = album.title
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserInfoVH {
        val view = AlbumItmeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserInfoVH(view)
    }

    override fun onBindViewHolder(holder: UserInfoVH, position: Int) {
        val album = albumList[position]
        holder.bind(album)
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    fun setAlbumList(albums: List<Album>) {
       albumList.clear()
        albumList.addAll(albums)
        notifyDataSetChanged()
    }
}