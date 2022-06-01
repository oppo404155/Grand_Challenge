package com.example.grandchallenge.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.grandchallenge.feature1.data.local.UsersDB
import com.example.grandchallenge.feature1.data.remote.UsersAPI
import com.example.grandchallenge.feature1.data.repos.UserPhotoRepoImpl
import com.example.grandchallenge.feature1.data.repos.UserProfileRepoImp
import com.example.grandchallenge.feature1.domain.repos.UserPhotosRepo
import com.example.grandchallenge.feature1.domain.repos.UserProfileRepo
import com.example.grandchallenge.feature1.domain.usecases.FetchAlbumPhotosUC
import com.example.grandchallenge.feature1.domain.usecases.FetchUserProfileUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiObject {
    @Provides
    @Singleton
    fun provideDataBase(context: Application): UsersDB =
        Room.databaseBuilder(context.applicationContext, UsersDB::class.java, "UsersDB")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun ProvideApi(): UsersAPI = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create()).build().create(UsersAPI::class.java)

    @Provides
    @Singleton
    fun providesUserProfileRepo(api: UsersAPI, db: UsersDB): UserProfileRepo =
        UserProfileRepoImp(db, api)

    @Provides
    @Singleton
    fun providesPhotoRepo(api: UsersAPI, db: UsersDB): UserPhotosRepo =
        UserPhotoRepoImpl(db, api)
    @Provides
    @Singleton
    fun providesUserInfoUC(repo:UserProfileRepo)=FetchUserProfileUC(repo)
    @Provides
    @Singleton
    fun providesFetchPhotosUC(repo:UserPhotosRepo)=FetchAlbumPhotosUC(repo)
}
