package com.example.ajarin.domain.validation.use_cases

import com.example.ajarin.domain.validation.utils.ValidationError

class ValidateNumber {
    operator fun invoke(
        number: String
    ): Result<Unit> {
        if (!number.startsWith("+")) {
            return Result.failure(
                ValidationError("Number should starts with \"+\"")
            )
        }

        if (number.length < 10) {
            return Result.failure(
                ValidationError("Number used to has at least 10 digits")
            )
        }

        return Result.success(Unit)
    }
}