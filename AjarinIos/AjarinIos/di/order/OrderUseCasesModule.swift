//
//  OrderUseCasesModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 18/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class OrderUseCasesModule {
    init() {
        @Inject var repository: OrderRepository
        
        @Provider var getUserOrders: GetUserOrders = GetUserOrders(repository: repository)
        @Provider var getMentorOrders: GetMentorOrders = GetMentorOrders(repository: repository)
        @Provider var getOrderById: GetOrderById = GetOrderById(repository: repository)
        @Provider var createOrder: CreateOrder = CreateOrder(repository: repository)
        @Provider var updateOrder: UpdateOrder = UpdateOrder(repository: repository)
    }
}
