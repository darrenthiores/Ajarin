//
//  MentorProfileUseCasesModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 22/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class MentorProfileUseCasesModule {
    init() {
        @Provider var getMentorById: GetMentorById = GetMentorById()
    }
}
