//
//  UserUseCasesModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 18/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class UserUseCasesModule {
    init() {
        @Inject var repository: UserRepository
        
        @Provider var getUser: GetUser = GetUser(repository: repository)
        @Provider var getUserById: GetUserById = GetUserById(repository: repository)
    }
}
