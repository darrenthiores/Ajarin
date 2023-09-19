//
//  ReviewUseCasesModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 18/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class ReviewUseCasesModule {
    init() {
        @Inject var repository: ReviewRepository
        
        @Provider var getMentorReviews: GetMentorReviews = GetMentorReviews(repository: repository)
        @Provider var createReview: CreateReview = CreateReview(repository: repository)
    }
}
