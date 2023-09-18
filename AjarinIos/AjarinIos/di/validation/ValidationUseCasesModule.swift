//
//  ValidationUseCasesModule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 18/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class ValidationUseCasesModule {
    init() {
        @Provider var validateNumber: ValidateNumber = ValidateNumber()
        @Provider var validatePassword: ValidatePassword = ValidatePassword()
        @Provider var validateEmail: ValidateEmail = ValidateEmail()
        @Provider var validateId: ValidateId = ValidateId()
        @Provider var validationUseCases: ValidationUseCases = ValidationUseCases(
            validateNumber: validateNumber,
            validatePassword: validatePassword,
            validateEmail: validateEmail,
            validateId: validateId
        )
    }
}
