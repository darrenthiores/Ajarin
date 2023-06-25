//
//  InboxRepositoryModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 25/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class InboxRepositoryModule {
    init() {
        @Inject var localDataSource: InboxLocalDataSource
        
        @Provider var inboxRepository: InboxRepository = InboxRepositoryImpl(
            localDataSource: localDataSource
        )
    }
}
