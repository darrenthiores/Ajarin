//
//  IosMentorProfileState.swift
//  AjarinIos
//
//  Created by Darren Thiores on 20/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

struct IosMentorProfileState {
    var currentReviewPage: Int = 1
    var endReviewReached: Bool = false
    var reviews: [Review] = []
    var isFetchingReviews: Bool = false
    var reviewError: Error? = nil
}

