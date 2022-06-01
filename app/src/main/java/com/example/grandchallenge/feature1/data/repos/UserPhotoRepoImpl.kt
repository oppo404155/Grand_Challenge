package com.example.grandchallenge.feature1.data.repos

import com.example.grandchallenge.feature1.data.local.UsersDB
import com.example.grandchallenge.feature1.data.remote.UsersAPI
import com.example.grandchallenge.feature1.domain.models.Photo
import com.example.grandchallenge.feature1.domain.repos.UserPhotosRepo
import com.example.grandchallenge.feature1.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserPhotoRepoImpl @Inject constructor(
    private val db: UsersDB,
    private val api: UsersAPI
) : UserPhotosRepo {
    override suspend fun getUserPhotos(id: Int): Flow<Resource<List<Photo>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val cashedPhotos = db.getPhotosDao().getAlbumPhotos(id)
            emit(Resource.Successes(data = cashedPhotos))
            val isLocalCashEmpty = cashedPhotos.isEmpty()
            val shouldFetchFromCash = !isLocalCashEmpty
            if (shouldFetchFromCash) {
                emit(Resource.Loading(isLoading = false))
                return@flow
            }
            val remotePhotoList = try {
                api.getPhotosOfAlbum(id)
            } catch (e: IOException) {
                emit(Resource.Error(error = e.localizedMessage))
                null
            } catch (e: HttpException) {
                emit(Resource.Error(error = e.localizedMessage))
                null
            }
            remotePhotoList?.let {
                db.getPhotosDao().clearPhotosFromCash()
                db.getPhotosDao().insertPhotosList(it)
                emit(Resource.Loading(isLoading = false))
                emit(Resource.Successes(data = db.getPhotosDao().getAlbumPhotos(albumId = id)))
            }


        }

    }
}