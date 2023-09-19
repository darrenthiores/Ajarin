//
//  OrderNetworkModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 18/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class OrderNetworkModule {
    init() {
        @Inject var dispatchers: DispatchersProvider
        @Inject var client: Ktor_client_coreHttpClient
        
        @Provider var OrderService: OrderService = KtorOrderService(client: client)
        @Provider var OrderRemoteDataSource: OrderRemoteDataSource = OrderRemoteDataSource(
            apiService: OrderService,
            dispatchers: dispatchers
        )
    }
}
