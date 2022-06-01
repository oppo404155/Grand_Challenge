package com.example.grandchallenge.feature1.domain.usecases

import com.example.grandchallenge.feature1.domain.repos.UserProfileRepo
import javax.inject.Inject

class FetchUserProfileUC @Inject constructor(private val userProfileRepo: UserProfileRepo) {
    suspend operator fun invoke(userId: Int) = userProfileRepo.getUsersInfo(userId)

}