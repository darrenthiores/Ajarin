package com.example.ajarin.domain.validation.use_cases

import com.example.ajarin.domain.validation.utils.ValidationError

class ValidateId {
    operator fun invoke(
        id: String
    ): Result<Unit> {
        if (id.length != 16) {
            return Result.failure(
                ValidationError("Id must be 16 digits")
            )
        }

        return Result.success(Unit)
    }
}