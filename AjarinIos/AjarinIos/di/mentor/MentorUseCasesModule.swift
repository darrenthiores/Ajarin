//
//  MentorUseCasesModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 18/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class MentorUseCasesModule {
    init() {
        @Provider var searchMentor: SearchMentor = SearchMentor()
        @Provider var searchMentorByCourse: SearchMentorByCourse = SearchMentorByCourse()
        @Provider var getMentorById: GetMentorById = GetMentorById()
    }
}
