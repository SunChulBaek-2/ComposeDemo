package kr.pe.ssun.composedemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kr.pe.ssun.composedemo.domain.SearchParam
import kr.pe.ssun.composedemo.domain.SearchResult
import kr.pe.ssun.composedemo.domain.SearchUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
): ViewModel() {

    private val _searchResult = MutableStateFlow<SearchResult?>(null)
    val searchResult: StateFlow<SearchResult?> = _searchResult

    suspend fun search(query: String) = onMain {
        searchUseCase(SearchParam(query, 100, 1, "date")).collect { result ->
            when {
                result.isSuccess -> {
                    _searchResult.emit(result.getOrNull())
                }
                result.isFailure -> {
                    _searchResult.emit(null)
                }
            }
        }
    }
}