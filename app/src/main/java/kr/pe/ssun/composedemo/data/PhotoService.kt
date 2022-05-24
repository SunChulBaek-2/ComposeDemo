package kr.pe.ssun.composedemo.data

import kr.pe.ssun.composedemo.data.model.Photo
import retrofit2.http.GET

interface PhotoService {

    @GET("photos")
    suspend fun getPhotos(): List<Photo>
}