package com.example.grandchallenge.feature1.presentation.ui.detailsFragment

import android.app.ProgressDialog
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grandchallenge.R
import com.example.grandchallenge.databinding.FragmentPhotosBinding
import com.example.grandchallenge.feature1.presentation.adapters.PhotosAdapter
import com.example.grandchallenge.feature1.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Suppress("DEPRECATION")
class PhotosFragment : Fragment(R.layout.fragment_photos) {

    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding
    private val photoAdapter = PhotosAdapter()
    lateinit var dialog:ProgressDialog


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val selectedAlbumId=arguments?.get("id") as Int
        _binding = FragmentPhotosBinding.bind(view)
        val viewModel: UserViewModel by viewModels()
        dialog = ProgressDialog(requireContext())
        viewModel.getUserPhotos(selectedAlbumId)
        viewModel.photoState.observe(viewLifecycleOwner){state->
            if (!state.isLoading!!) {

                dialog.dismiss()
            }
            if (state.errorMessage != null) {
                dialog.dismiss()
                Toast.makeText(
                    requireContext(),
                    "some Error happened please check network connection and try again",
                    Toast.LENGTH_SHORT
                ).show()

            }
            if (state.photos!!.isNotEmpty()){
               photoAdapter.setPhotoList(state.photos!!)
                binding?.apply {
                    photoRecycler.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(requireContext(),2)
                        adapter = photoAdapter

                    }

                   searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
                       androidx.appcompat.widget.SearchView.OnQueryTextListener {
                       override fun onQueryTextSubmit(p0: String?): Boolean {
                           TODO("Not yet implemented")
                       }

                       override fun onQueryTextChange(p0: String?): Boolean {
                          photoAdapter.filter.filter(p0)
                           return false
                       }
                   })
                }

            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}