package com.example.ajarin.domain.validation.use_cases

import com.example.ajarin.domain.validation.utils.ValidationError

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