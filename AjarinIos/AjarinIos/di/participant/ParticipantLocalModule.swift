//
//  ParticipantLocalModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 18/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class ParticipantLocalModule {
    init() {
        @Inject var chatDatabase: ChatDatabase
        @Provider var participantDao: ParticipantDao = SqlDelightParticipantDao(db: chatDatabase)
        @Provider var participantLocalDataSource: ParticipantLocalDataSource = ParticipantLocalDataSource(
            dao: participantDao
        )
    }
}
