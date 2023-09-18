//
//  InboxUseCasesModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 25/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class InboxUseCasesModule {
    init() {
        @Inject var messageRepository: MessageRepository
        @Inject var inboxRepository: InboxRepository
        @Inject var participantRepository: ParticipantRepository
        
        @Provider var getInbox: GetInbox = GetInbox(repository: inboxRepository)
        @Provider var createInbox: CreateInbox = CreateInbox(
            messageRepository: messageRepository,
            inboxRepository: inboxRepository,
            participantRepository: participantRepository
        )
    }
}
