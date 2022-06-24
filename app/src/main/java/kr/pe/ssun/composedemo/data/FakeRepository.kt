package kr.pe.ssun.composedemo.data

import kr.pe.ssun.composedemo.data.model.Photo
import javax.inject.Singleton

@Singleton
class FakeRepository(
    private val apiService: ApiService
) {

    suspend fun getPhotos(): List<Photo> = apiService.getPhotos()
}