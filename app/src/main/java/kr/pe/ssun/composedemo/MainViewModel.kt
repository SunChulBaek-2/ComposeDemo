package kr.pe.ssun.composedemo

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.pe.ssun.composedemo.data.model.ShopItem
import kr.pe.ssun.composedemo.domain.GetShopParam
import kr.pe.ssun.composedemo.domain.GetShopUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getShopUseCase: GetShopUseCase
): ViewModel() {

    private val _items = mutableStateListOf<ShopItem>()
    val items: SnapshotStateList<ShopItem> = _items

    fun getShop(query: String) = onMain {
        getShopUseCase(GetShopParam(query)).collect { result ->
            when {
                result.isSuccess -> {
                    _items.clear()
                    _items.addAll(result.getOrNull() ?: listOf())
                }
                result.isFailure -> {
                    _items.clear()
                }
            }
        }
    }
}