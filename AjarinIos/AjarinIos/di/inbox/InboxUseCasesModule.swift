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
        @Inject var inboxRepository: InboxRepository
        
        @Provider var getInbox: GetInbox = GetInbox(repository: inboxRepository)
    }
}
