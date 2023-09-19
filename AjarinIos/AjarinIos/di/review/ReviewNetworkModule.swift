//
//  ReviewNetworkModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 18/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class ReviewNetworkModule {
    init() {
        @Inject var dispatchers: DispatchersProvider
        @Inject var client: Ktor_client_coreHttpClient
        
        @Provider var ReviewService: ReviewService = KtorReviewService(client: client)
        @Provider var ReviewRemoteDataSource: ReviewRemoteDataSource = ReviewRemoteDataSource(
            apiService: ReviewService,
            dispatchers: dispatchers
        )
    }
}
