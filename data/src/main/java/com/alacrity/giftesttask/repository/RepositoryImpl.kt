package com.alacrity.giftesttask.repository

import com.alacrity.giftesttask.api.GifsApi
import com.alacrity.giftesttask.entity.ApiResponse
import com.alacrity.giftesttask.entity.Gif
import com.alacrity.giftesttask.entity.GifDao
import com.alacrity.giftesttask.entity.toRawItems
import com.alacrity.giftesttask.exceptions.NoDataFromResponseException
import com.alacrity.giftesttask.util.toTableItem
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RepositoryImpl @Inject constructor(
    private val gifDao: GifDao,
    private val api: GifsApi
) : Repository {

    override suspend fun getGifs(query: String, offSet: Int, limit: Int): ApiResponse {
        val call = api.getGifs(query, offSet, limit)
        return suspendCoroutine { continuation ->
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val data = response.body()?.string()
                    data ?: continuation.resumeWithException(NoDataFromResponseException())
                        .also { return }

                    val result = data?.let { ApiResponse.fromJson(it) }
                    result?.let { continuation.resume(it) }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    override suspend fun getGifsFromDB(): List<Gif> = gifDao.getAll().toRawItems()

    override suspend fun saveItemToDB(gif: Gif) = gifDao.insertAll(gif.toTableItem())

    override suspend fun removeGifFromDB(gif: Gif) = gifDao.delete(gif.toTableItem())

}