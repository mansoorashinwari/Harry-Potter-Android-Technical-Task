package com.example.harrypottercaracters

import com.example.harrypottercaracters.networking.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {
    suspend fun getAllCharacters() = retrofitService.getAllCharacters()
}