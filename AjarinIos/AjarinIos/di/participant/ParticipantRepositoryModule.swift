//
//  ParticipantRepositoryModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 18/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class ParticipantRepositoryModule {
    init() {
        @Inject var localDataSource: ParticipantLocalDataSource
        
        @Provider var participantRepository: ParticipantRepository = ParticipantRepositoryImpl(
            localDataSource: localDataSource
        )
    }
}

