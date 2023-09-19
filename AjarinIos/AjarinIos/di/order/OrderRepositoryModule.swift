//
//  OrderRepositoryModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 18/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class OrderRepositoryModule {
    init() {
        @Inject var remoteDataSource: OrderRemoteDataSource
        
        @Provider var OrderRepository: OrderRepository = OrderRepositoryImpl(
            remoteDataSource: remoteDataSource
        )
    }
}
