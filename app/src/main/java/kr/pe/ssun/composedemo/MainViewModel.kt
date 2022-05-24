package kr.pe.ssun.composedemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kr.pe.ssun.composedemo.domain.GetPhotoParam
import kr.pe.ssun.composedemo.domain.GetPhotoResult
import kr.pe.ssun.composedemo.domain.GetPhotoUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPhotoUseCase: GetPhotoUseCase
): ViewModel() {

    private val _photos = MutableStateFlow<GetPhotoResult?>(null)
    val photos: StateFlow<GetPhotoResult?> = _photos

    suspend fun getPhotos() = viewModelScope.launch(Dispatchers.Main) {
        getPhotoUseCase(GetPhotoParam()).collect { result ->
            when {
                result.isSuccess -> {
                    _photos.emit(result.getOrNull())
                }
                result.isFailure -> {
                    _photos.emit(null)
                }
            }
        }
    }
}