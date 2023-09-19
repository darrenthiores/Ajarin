//
//  UserNetworkModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 18/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class UserNetworkModule {
    init() {
        @Inject var dispatchers: DispatchersProvider
        @Inject var client: Ktor_client_coreHttpClient
        
        @Provider var UserService: UserService = KtorUserService(client: client)
        @Provider var UserRemoteDataSource: UserRemoteDataSource = UserRemoteDataSource(
            apiService: UserService,
            dispatchers: dispatchers
        )
    }
}
