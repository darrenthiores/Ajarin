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
        @Inject var repository: MentorRepository
        
        @Provider var getMentor: GetMentor = GetMentor(repository: repository)
        @Provider var getMentorById: GetMentorById = GetMentorById(repository: repository)
        @Provider var getMentors: GetMentors = GetMentors(repository: repository)
        @Provider var searchMentor: SearchMentor = SearchMentor(repository: repository)
        @Provider var applyAsMentor: ApplyAsMentor = ApplyAsMentor(repository: repository)
    }
}
