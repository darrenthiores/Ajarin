//
//  MessageRepositoryModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 25/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class MessageRepositoryModule {
    init() {
        @Inject var localDataSource: MessageLocalDataSource
        
        @Provider var messageRepository: MessageRepository = MessageRepositoryImpl(
            localDataSource: localDataSource
        )
    }
}
