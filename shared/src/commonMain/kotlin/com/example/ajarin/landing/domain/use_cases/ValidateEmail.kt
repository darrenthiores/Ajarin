package com.example.ajarin.landing.domain.use_cases

import com.example.ajarin.core.utils.errors.ValidationError

class ValidateEmail {

    operator fun invoke(
        email: String
    ): Result<Unit> {
       if (!isValidEmail(email)) {
           return Result.failure(
               ValidationError("Email format is wrong!")
           )
       }

        return Result.success(Unit)
    }

    private fun isValidEmail(email: String): Boolean {
        val emailAddressRegex = Regex(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )

        return email.matches(emailAddressRegex)
    }
}