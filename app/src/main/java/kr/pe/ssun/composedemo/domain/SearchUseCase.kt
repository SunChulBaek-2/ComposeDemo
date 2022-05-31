package kr.pe.ssun.composedemo.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.pe.ssun.composedemo.data.SearchRepository
import kr.pe.ssun.composedemo.data.model.ShopItem
import javax.inject.Inject

data class SearchParam(
    val query: String,
    val display: Int,
    val start: Long,
    val sort: String
)

data class SearchResult(
    val photos: List<ShopItem>
)

class SearchUseCase @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val repository: SearchRepository
): FlowUseCase<SearchParam, SearchResult>(dispatcher) {

    override fun execute(parameters: SearchParam): Flow<Result<SearchResult>> = flow {
        emit(Result.success(SearchResult(repository.search(parameters.query, parameters.display, parameters.start, parameters.sort).items)))
    }
}