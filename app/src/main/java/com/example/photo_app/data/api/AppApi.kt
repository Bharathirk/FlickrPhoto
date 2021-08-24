package com.example.photo_app.data.api

import com.example.photo_app.data.response.photo.PhotoResponse
import retrofit2.http.*

interface AppApi {
    @GET(ApiConstants.API_COURSE)
    suspend fun getPhotoList(@Query("format")searchquery:String,@Query("tags")page:String,@Query("nojsoncallback")jsonCallBack:String): PhotoResponse
}