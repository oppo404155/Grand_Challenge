package com.example.grandchallenge.feature1.data.repos

import android.util.Log
import com.example.grandchallenge.feature1.data.local.UsersDB
import com.example.grandchallenge.feature1.data.remote.UsersAPI
import com.example.grandchallenge.feature1.domain.models.Album
import com.example.grandchallenge.feature1.domain.models.Photo
import com.example.grandchallenge.feature1.domain.models.User
import com.example.grandchallenge.feature1.domain.models.UserInfoResponse
import com.example.grandchallenge.feature1.domain.repos.UserProfileRepo
import com.example.grandchallenge.feature1.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserProfileRepoImp @Inject constructor(
    private val db: UsersDB,
    private val api: UsersAPI

) : UserProfileRepo {
    override suspend fun getUsersInfo(userId: Int): Flow<Resource<UserInfoResponse>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val listFromCash = db.getUserDAO().getUsersFromCash()
            val isLocalDbEmpty = listFromCash.isEmpty()
            var localAlbums: List<Album> = emptyList()
            val shouldFetchFromCash = !isLocalDbEmpty
            if (shouldFetchFromCash) {
                localAlbums = db.getAlbumDao().getAlbumsFromCash(listFromCash[userId].id)
                emit(Resource.Loading(isLoading = false))
                emit(Resource.Successes(UserInfoResponse(listFromCash[userId], localAlbums)))
                return@flow

            }
            val remoteUser = try {
                api.getUsers()
            } catch (e: HttpException) {
                emit(Resource.Error(error = e.localizedMessage))
                null
            } catch (e: IOException) {
                emit(Resource.Error(error = e.localizedMessage))
                null
            }

            remoteUser?.let { users ->
                val remoteAlbums = try {
                    api.getUserAlbums(users[userId].id ?: 1)
                } catch (e: HttpException) {
                    emit(Resource.Error(error = e.localizedMessage))
                    null
                } catch (e: IOException) {
                    emit(Resource.Error(error = e.localizedMessage))
                    null
                }
                remoteAlbums?.let { userAlbums ->
                    db.getUserDAO().clearUserEntityCash()
                    db.getUserDAO().insertUsersToCash(users)

                    db.getAlbumDao().clearAlbumsFromCash()
                    db.getAlbumDao().insertAlbumsToCash(userAlbums)


                    val user = db.getUserDAO().getUsersFromCash()[userId]
                    val albums = db.getAlbumDao().getAlbumsFromCash(user.id)
                    emit((Resource.Loading(isLoading = false)))
                    emit(Resource.Successes(UserInfoResponse(user = user, albums = albums)))


                }

            }


        }
    }


}