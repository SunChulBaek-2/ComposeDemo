package kr.pe.ssun.composedemo.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.pe.ssun.composedemo.data.FakeRepository
import kr.pe.ssun.composedemo.data.model.Photo
import javax.inject.Inject

class GetPhotoParam

class GetPhotoUseCase @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val repository: FakeRepository
): FlowUseCase<GetPhotoParam, List<Photo>>(dispatcher) {

    override fun execute(parameters: GetPhotoParam): Flow<Result<List<Photo>>> = flow {
        try {
            emit(Result.success(repository.getPhotos()))
        } catch (e: Exception) {
            emit(Result.failure(Throwable(e.message)))
        }
    }
}