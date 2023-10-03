package com.example.ajarin.android.core.domain.use_cases

import androidx.paging.PagingData
import com.example.ajarin.android.core.domain.repository.CoreRepository
import com.example.ajarin.domain.mentor.model.Mentor
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class AndroidGetMentors @Inject constructor(
    private val repository: CoreRepository
) {
    operator fun invoke(): Flow<PagingData<Mentor>> {
        return repository
            .getMentors()
    }
}