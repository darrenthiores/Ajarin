package com.example.ajarin.android.core.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ajarin.android.core.utils.CustomException
import com.example.ajarin.data.core.utils.ApiResponse
import com.example.ajarin.data.order.mapper.toOrder
import com.example.ajarin.data.order.remote.OrderRemoteDataSource
import com.example.ajarin.domain.order.model.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class GetUserOrdersPagingSource(
    private val remoteDataSource: OrderRemoteDataSource
): PagingSource<Int, Order>() {

    override fun getRefreshKey(state: PagingState<Int, Order>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Order> {
        return withContext(Dispatchers.IO) {
            val page = params.key ?: 1

            Timber.d("ON GET USER ORDERS: $page")
            try {
                val response = remoteDataSource.getUserOrders(page)

                when (response) {
                    ApiResponse.Empty -> {
                        LoadResult.Page(
                            data = emptyList(),
                            prevKey = if(page==1) null else page.minus(1),
                            nextKey = null
                        )
                    }
                    is ApiResponse.Error -> {
                        throw CustomException("ON GET USER ORDERS ERROR: ${response.errorMessage}")
                    }
                    is ApiResponse.Success -> {
                        val mentors = response.data

                        val nextKey = if(mentors.isEmpty()) {
                            null
                        } else {
                            page.plus(1) //+ (params.loadSize / LOAD_SIZE)
                        }

                        LoadResult.Page(
                            data = mentors
                                .map {
                                     it.toOrder()
                                },
                            prevKey = if(page==1) null else page.minus(1),
                            nextKey = nextKey
                        )
                    }
                }
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }
}