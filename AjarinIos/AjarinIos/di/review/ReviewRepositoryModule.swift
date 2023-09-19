//
//  ReviewRepositoryModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 18/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class ReviewRepositoryModule {
    init() {
        @Inject var remoteDataSource: ReviewRemoteDataSource
        
        @Provider var ReviewRepository: ReviewRepository = ReviewRepositoryImpl(
            remoteDataSource: remoteDataSource
        )
    }
}
