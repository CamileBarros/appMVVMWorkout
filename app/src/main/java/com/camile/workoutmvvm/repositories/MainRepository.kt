package com.camile.workoutmvvm.repositories

import androidx.lifecycle.ViewModel
import com.camile.workoutmvvm.rest.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllLives() = retrofitService.getAllives()
}