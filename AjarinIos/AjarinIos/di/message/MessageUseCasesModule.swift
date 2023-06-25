//
//  MessageUseCasesModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 25/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class MessageUseCasesModule {
    init() {
        @Inject var messageRepository: MessageRepository
        
        @Provider var getMessagesById: GetMessagesById = GetMessagesById(repository: messageRepository)
        @Provider var createInbox: CreateInbox = CreateInbox(repository: messageRepository)
        @Provider var insertMessage: InsertMessage = InsertMessage(repository: messageRepository)
    }
}
