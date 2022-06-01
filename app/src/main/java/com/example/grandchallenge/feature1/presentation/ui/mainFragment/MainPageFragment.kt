package com.example.grandchallenge.feature1.presentation.ui.mainFragment

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grandchallenge.R
import com.example.grandchallenge.databinding.FragmentMainPageBinding
import com.example.grandchallenge.feature1.presentation.adapters.UserInfoAdapter
import com.example.grandchallenge.feature1.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Suppress("DEPRECATION")
class MainPageFragment : Fragment(R.layout.fragment_main_page) {
    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding
    lateinit var dialog: ProgressDialog
    private val userAdapter = UserInfoAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentMainPageBinding.bind(view)
        val viewModel: UserViewModel by viewModels()
        dialog = ProgressDialog(requireContext())

        viewModel.getUsersInfo(1)
        dialog.show()

        viewModel.userState.observe(requireActivity()) { state ->
            // Log.d("LisOfAlbums", state.isLoading.toString())
            Log.d("LisOfAlbums", state.albums.toString())
            Log.d("LisOfAlbums", state.errorMessge.toString())
            if (!state.isLoading!!) {

                dialog.dismiss()
            }
            if (state.errorMessge != null) {
                dialog.dismiss()
                Toast.makeText(
                    requireContext(),
                    "some Error happened please please try again or check network connection",
                    Toast.LENGTH_SHORT
                ).show()

            }

            if (state.user != null && state.albums!!.isNotEmpty()) {
                userAdapter.setAlbumList(state.albums!!)
                dialog.dismiss()
                binding?.apply {
                    addressTxv.text =
                        "${state.user?.address?.city}/${state.user?.address!!.street}/${state.user?.address?.suite}"

                    usernameTxv.text = state.user?.name
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = userAdapter
                    }


                }
            }



        }
        userAdapter.onItemClicked = {
            val albumId = bundleOf("id" to 3)
            Log.d("ARRRRR",it.id.toString())
            findNavController().navigate(
                R.id.action_mainPageFragment_to_photosFragment,albumId
            )

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
