//
//  SessionUseCasesModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 23/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class SessionUseCasesModule {
    init() {
        @Provider var getSessionById: GetSessionById = GetSessionById()
    }
}
