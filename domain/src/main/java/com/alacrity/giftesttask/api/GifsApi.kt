package com.alacrity.giftesttask.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GifsApi {

    @GET("/v1/gifs/search")
    fun getGifs(
        @Query("q") query: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("api_key") key: String = API_KEY): Call<ResponseBody>

    companion object {
        const val API_KEY = "gYQeJMYfr28iVXDIwCHmGrTiVAuSjzLi"
    }
}