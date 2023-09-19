//
//  UserRepositoryModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 18/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class UserRepositoryModule {
    init() {
        @Inject var remoteDataSource: UserRemoteDataSource
        
        @Provider var UserRepository: UserRepository = UserRepositoryImpl(
            remoteDataSource: remoteDataSource
        )
    }
}
