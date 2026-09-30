package com.shenawynkov.procoretest.networking

import com.shenawynkov.procoretest.data.models.PekomonResponse
import retrofit2.http.GET

interface ApiService {

    @GET("v2/cards")
    suspend fun getPokemons(): PekomonResponse
}