//
//  IosSearchMentorState.swift
//  AjarinIos
//
//  Created by Darren Thiores on 20/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

struct IosSearchMentorState {
    var currentMentorPage: Int = 1
    var endMentorReached: Bool = false
    var mentors: [Mentor] = []
    var isFetchingMentors: Bool = false
    var mentorError: Error? = nil
}
