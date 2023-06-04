package com.example.ajarin.applyAsMentor.domain.use_cases

import com.example.ajarin.core.utils.errors.ValidationError

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