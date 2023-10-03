//
//  IosHistoryState.swift
//  AjarinIos
//
//  Created by Darren Thiores on 20/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

struct IosHistoryState {
    var currentUserOrderPage: Int = 1
    var endUserOrderReached: Bool = false
    var userOrders: [Order] = []
    var isFetchingUserOrders: Bool = false
    var userOrderError: Error? = nil
    var currentMentorOrdersPage: Int = 1
    var endMentorOrdersReached: Bool = false
    var mentorOrders: [Order] = []
    var isFetchingMentorOrders: Bool = false
    var mentorOrderError: Error? = nil
}
