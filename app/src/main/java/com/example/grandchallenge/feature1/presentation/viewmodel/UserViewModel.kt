package com.example.grandchallenge.feature1.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grandchallenge.feature1.domain.models.Album
import com.example.grandchallenge.feature1.domain.repos.UserPhotosRepo
import com.example.grandchallenge.feature1.domain.repos.UserProfileRepo
import com.example.grandchallenge.feature1.domain.usecases.FetchAlbumPhotosUC
import com.example.grandchallenge.feature1.domain.usecases.FetchUserProfileUC
import com.example.grandchallenge.feature1.presentation.ui.detailsFragment.PhotoState
import com.example.grandchallenge.feature1.presentation.ui.mainFragment.UserState
import com.example.grandchallenge.feature1.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val profileUC: FetchUserProfileUC,
    private val photosUC: FetchAlbumPhotosUC
) :
    ViewModel() {
    val userState = MutableLiveData<UserState>()
    val photoState = MutableLiveData<PhotoState>()

    fun getUsersInfo(userId: Int) {
        viewModelScope.launch {
            profileUC(userId).collect { result ->
                when (result) {
                    is Resource.Successes -> {
                        userState.value = UserState(result.data?.user, result.data?.albums)

                    }
                    is Resource.Loading -> {
                        userState.value = UserState(isLoading = result.isLoading)
                    }
                    is Resource.Error -> {
                        userState.value = UserState(errorMessge = result.error.toString())

                    }
                }

            }

        }

    }

    fun getUserPhotos(id: Int) {
        viewModelScope.launch {
            photosUC(id).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        photoState.value = PhotoState(isLoading = result.isLoading)
                    }
                    is Resource.Successes -> {
                        photoState.value = PhotoState(photos = result.data)

                    }

                    is Resource.Error -> {
                        photoState.value = PhotoState(errorMessage = result.error.toString())

                    }
                }

            }
        }

    }

}