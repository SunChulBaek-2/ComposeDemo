package kr.pe.ssun.composedemo

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.pe.ssun.composedemo.data.model.Photo
import kr.pe.ssun.composedemo.domain.GetPhotoParam
import kr.pe.ssun.composedemo.domain.GetPhotoUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPhotoUseCase: GetPhotoUseCase
): ViewModel() {

    private val _searchResult = mutableStateListOf<Photo>()
    val searchResult: SnapshotStateList<Photo> = _searchResult

    suspend fun search() = onMain {
        getPhotoUseCase(GetPhotoParam()).collect { result ->
            when {
                result.isSuccess -> {
                    _searchResult.clear()
                    _searchResult.addAll(result.getOrNull() ?: listOf())
                }
                result.isFailure -> {
                    _searchResult.clear()
                }
            }
        }
    }
}