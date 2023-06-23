//
//  HistoryUseCasesModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 23/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class HistoryUseCasesModule {
    init() {
        @Provider var getHistory: GetHistory = GetHistory()
    }
}
