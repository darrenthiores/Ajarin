package com.example.ajarin.android.di.mentor

import com.example.ajarin.domain.mentor.repository.MentorRepository
import com.example.ajarin.domain.mentor.use_cases.ApplyAsMentor
import com.example.ajarin.domain.mentor.use_cases.GetMentor
import com.example.ajarin.domain.mentor.use_cases.GetMentorById
import com.example.ajarin.domain.mentor.use_cases.GetMentors
import com.example.ajarin.domain.mentor.use_cases.SearchMentor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MentorUseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetMentorUseCase(
        repository: MentorRepository
    ): GetMentor {
        return GetMentor(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetMentorByIdUseCase(
        repository: MentorRepository
    ): GetMentorById {
        return GetMentorById(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideGetMentorsUseCase(
        repository: MentorRepository
    ): GetMentors {
        return GetMentors(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideSearchMentorUseCase(
        repository: MentorRepository
    ): SearchMentor {
        return SearchMentor(
            repository = repository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideApplyAsMentorUseCase(
        repository: MentorRepository
    ): ApplyAsMentor {
        return ApplyAsMentor(
            repository = repository
        )
    }
}