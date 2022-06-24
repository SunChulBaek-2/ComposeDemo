package kr.pe.ssun.composedemo.data

import kr.pe.ssun.composedemo.data.model.Photo
import retrofit2.http.GET

interface ApiService {

    @GET("photos")
    suspend fun getPhotos(): List<Photo>
}