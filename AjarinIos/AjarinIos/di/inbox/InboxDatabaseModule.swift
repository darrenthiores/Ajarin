//
//  InboxDatabaseModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 25/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class InboxDatabaseModule {
    init() {
        @Inject var chatDatabase: ChatDatabase
        @Provider var inboxChatDao: InboxChatDao = SqlDelightInboxChatDao(db: chatDatabase)
        @Provider var inboxLocalDataSource: InboxLocalDataSource = InboxLocalDataSource(
            dao: inboxChatDao
        )
    }
}
