package com.example.ajarin.android.di.order

import com.example.ajarin.data.order.remote.OrderRemoteDataSource
import com.example.ajarin.data.order.remote.service.KtorOrderService
import com.example.ajarin.data.order.remote.service.OrderService
import com.example.ajarin.domain.core.dispatchers.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderRemoteModule {
    @Provides
    @Singleton
    fun provideOrderService(
        client: HttpClient
    ): OrderService {
        return KtorOrderService(client)
    }

    @Provides
    @Singleton
    fun provideOrderRemoteDataSource(
        service: OrderService,
        dispatchers: DispatchersProvider
    ): OrderRemoteDataSource {
        return OrderRemoteDataSource(
            apiService = service,
            dispatchers = dispatchers
        )
    }
}