package com.camile.workoutmvvm.repositories

import com.camile.workoutmvvm.rest.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllLives() = retrofitService.getAllLives()
}