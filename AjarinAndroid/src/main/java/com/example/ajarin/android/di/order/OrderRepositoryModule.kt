package com.example.ajarin.android.di.order

import com.example.ajarin.data.order.remote.OrderRemoteDataSource
import com.example.ajarin.data.order.repository.OrderRepositoryImpl
import com.example.ajarin.domain.order.repository.OrderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderRepositoryModule {

    @Provides
    @Singleton
    fun provideOrderRepository(
        remoteDataSource: OrderRemoteDataSource
    ): OrderRepository {
        return OrderRepositoryImpl(
            remoteDataSource = remoteDataSource
        )
    }
}