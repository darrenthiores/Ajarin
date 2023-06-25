//
//  MessageDatabaseModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 25/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class MessageDatabaseModule {
    init() {
        @Inject var chatDatabase: ChatDatabase
        @Provider var messageChatDao: MessageChatDao = SqlDelightMessageChatDao(db: chatDatabase)
        @Provider var messageLocalDataSource: MessageLocalDataSource = MessageLocalDataSource(
            chatDao: messageChatDao
        )
    }
}
