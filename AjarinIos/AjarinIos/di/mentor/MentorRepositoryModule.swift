//
//  MentorRepositoryModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 18/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class MentorRepositoryModule {
    init() {
        @Inject var remoteDataSource: MentorRemoteDataSource
        
        @Provider var MentorRepository: MentorRepository = MentorRepositoryImpl(
            remoteDataSource: remoteDataSource
        )
    }
}
