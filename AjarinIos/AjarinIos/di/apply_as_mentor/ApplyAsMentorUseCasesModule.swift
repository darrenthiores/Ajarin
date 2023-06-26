//
//  ApplyAsMentorUseCasesModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class ApplyAsMentorUseCasesModule {
    init() {
        @Provider var validateId: ValidateId = ValidateId()
    }
}
