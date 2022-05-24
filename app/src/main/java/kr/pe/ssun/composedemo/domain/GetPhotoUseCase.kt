package kr.pe.ssun.composedemo.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.pe.ssun.composedemo.data.PhotoRepository
import kr.pe.ssun.composedemo.data.model.Photo
import javax.inject.Inject

class GetPhotoParam

data class GetPhotoResult(
    val photos: List<Photo>
)

class GetPhotoUseCase @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val repository: PhotoRepository
): FlowUseCase<GetPhotoParam, GetPhotoResult>(dispatcher) {

    override fun execute(parameters: GetPhotoParam): Flow<Result<GetPhotoResult>> = flow {
        emit(Result.success(GetPhotoResult(repository.getPhotos())))
    }
}