package com.example.ajarin.android.core.domain.use_cases

import androidx.paging.PagingData
import com.example.ajarin.android.core.domain.repository.CoreRepository
import com.example.ajarin.domain.order.model.Order
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class AndroidGetUserOrders @Inject constructor(
    private val repository: CoreRepository
) {
    operator fun invoke(): Flow<PagingData<Order>> {
        return repository
            .getUserOrders()
    }
}