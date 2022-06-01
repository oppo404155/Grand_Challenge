package com.example.grandchallenge.feature1.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.grandchallenge.R
import com.example.grandchallenge.databinding.PhotoItemBinding
import com.example.grandchallenge.feature1.domain.models.Photo

class PhotosAdapter : RecyclerView.Adapter<PhotosAdapter.PhotosVH>(),Filterable {
    private val photoList = ArrayList<Photo>()
    private lateinit var PhotoListFull:List<Photo>
    var onItemClicked: ((String) -> Unit)? = null

    inner class PhotosVH(private val binding: PhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {

            }
        }

        fun bind(photo: Photo) {
            binding.apply {
                Glide.with(itemView).load(photo.thumbnailUrl).centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade()).
                    error(R.drawable.error_24).into(imageView)
                Log.d("GlideURL",photo.thumbnailUrl)
                titleTxv.text = photo.title

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosVH {
        val view = PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotosVH(view)
    }

    override fun onBindViewHolder(holder: PhotosVH, position: Int) {
        holder.bind(photoList[position])
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    fun setPhotoList(photos: List<Photo>) {
        photoList.clear()
        photoList.addAll(photos)
        //copy of wordList for filtering
        PhotoListFull = ArrayList<Photo>(photoList)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
       return photoFilter
    }
    private val photoFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<Photo> = ArrayList()
            if (constraint.isEmpty()) {
                filteredList.addAll(PhotoListFull)
            } else {
                val filterPattern = constraint.toString().lowercase().trim()
                for (item in PhotoListFull) {
                    if (item.title.lowercase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            photoList.clear()
            photoList.addAll(results.values as List<Photo>)
            notifyDataSetChanged()
        }
    }

}