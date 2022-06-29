package kr.pe.ssun.composedemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.pe.ssun.composedemo.data.model.ShopItem
import kr.pe.ssun.composedemo.domain.GetShopParam
import kr.pe.ssun.composedemo.domain.GetShopUseCase
import javax.inject.Inject

data class MainUiState(
    val items: List<ShopItem> = listOf()
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getShopUseCase: GetShopUseCase
): ViewModel() {

    var uiState by mutableStateOf(MainUiState())
        private set

    fun getShop(query: String) = onMain {
        getShopUseCase(GetShopParam(query)).collect { result ->
            when {
                result.isSuccess -> {
                    uiState = MainUiState(result.getOrNull() ?: listOf())
                }
                result.isFailure -> {
                    uiState = MainUiState()
                }
            }
        }
    }
}