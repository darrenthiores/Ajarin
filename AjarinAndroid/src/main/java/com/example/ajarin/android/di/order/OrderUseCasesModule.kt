package com.example.ajarin.android.di.order

import com.example.ajarin.domain.order.repository.OrderRepository
import com.example.ajarin.domain.order.use_cases.CreateOrder
import com.example.ajarin.domain.order.use_cases.GetMentorOrders
import com.example.ajarin.domain.order.use_cases.GetOrderById
import com.example.ajarin.domain.order.use_cases.GetUserOrders
import com.example.ajarin.domain.order.use_cases.UpdateOrder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OrderUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetUserOrdersUseCase(
        repository: OrderRepository
    ): GetUserOrders {
        return GetUserOrders(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetMentorOrdersUseCase(
        repository: OrderRepository
    ): GetMentorOrders {
        return GetMentorOrders(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetOrderByIdUseCase(
        repository: OrderRepository
    ): GetOrderById {
        return GetOrderById(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideCreateOrderUseCase(
        repository: OrderRepository
    ): CreateOrder {
        return CreateOrder(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideUpdateOrderUseCase(
        repository: OrderRepository
    ): UpdateOrder {
        return UpdateOrder(
            repository = repository
        )
    }
}