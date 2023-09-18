//
//  AuthUseCasesModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 18/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class AuthUseCasesModule {
    init() {
        @Inject var authRepository: AuthRepository
        @Inject var tokenPreferences: TokenPreferences
        
        @Provider var login: Login = Login(
            repository: authRepository,
            tokenPreferences: tokenPreferences
        )
        @Provider var register: Register = Register(
            repository: authRepository,
            tokenPreferences: tokenPreferences
        )
    }
}
