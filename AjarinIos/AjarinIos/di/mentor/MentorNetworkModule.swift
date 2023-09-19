//
//  MentorNetworkModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 18/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class MentorNetworkModule {
    init() {
        @Inject var dispatchers: DispatchersProvider
        @Inject var client: Ktor_client_coreHttpClient
        
        @Provider var MentorService: MentorService = KtorMentorService(client: client)
        @Provider var MentorRemoteDataSource: MentorRemoteDataSource = MentorRemoteDataSource(
            apiService: MentorService,
            dispatchers: dispatchers
        )
    }
}
