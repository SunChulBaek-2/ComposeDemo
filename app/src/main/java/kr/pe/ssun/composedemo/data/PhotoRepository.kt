package kr.pe.ssun.composedemo.data

import kr.pe.ssun.composedemo.data.model.Photo
import javax.inject.Singleton

@Singleton
class PhotoRepository(
    private val photoService: PhotoService
) {

    suspend fun getPhotos(): List<Photo> = photoService.getPhotos()
}