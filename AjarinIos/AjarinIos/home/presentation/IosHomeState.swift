//
//  IosHomeState.swift
//  AjarinIos
//
//  Created by Darren Thiores on 20/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

struct IosHomeState {
    var currentMentorPage: Int = 1
    var endMentorReached: Bool = false
    var mentors: [Mentor] = []
    var isFetchingMentors: Bool = false
    var mentorError: Error? = nil
    var currentSearchMentorPage: Int = 1
    var endSearchMentorReached: Bool = false
    var searchMentors: [Mentor] = []
    var isFetchingSearchMentors: Bool = false
    var searchMentorError: Error? = nil
}
