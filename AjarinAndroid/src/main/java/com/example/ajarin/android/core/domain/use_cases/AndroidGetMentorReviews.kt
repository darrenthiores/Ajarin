package com.example.ajarin.android.core.domain.use_cases

import androidx.paging.PagingData
import com.example.ajarin.android.core.domain.repository.CoreRepository
import com.example.ajarin.domain.review.model.Review
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class AndroidGetMentorReviews @Inject constructor(
    private val repository: CoreRepository
) {
    operator fun invoke(
        id: String
    ): Flow<PagingData<Review>> {
        return repository
            .getMentorReviews(
                id = id
            )
    }
}