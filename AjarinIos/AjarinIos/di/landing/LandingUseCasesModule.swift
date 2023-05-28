import Foundation
import shared

class LandingUseCasesModule {
    init() {
        @Provider var validateNumber: ValidateNumber = ValidateNumber()
        @Provider var validatePassword: ValidatePassword = ValidatePassword()
        @Provider var validateEmail: ValidateEmail = ValidateEmail()
        @Provider var landingUseCases: LandingUseCases = LandingUseCases(
            validateNumber: validateNumber,
            validatePassword: validatePassword,
            validateEmail: validateEmail
        )
    }
}
