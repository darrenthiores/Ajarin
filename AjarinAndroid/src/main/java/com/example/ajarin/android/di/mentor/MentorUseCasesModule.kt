package com.example.ajarin.android.di.mentor

import com.example.ajarin.domain.mentor.use_cases.GetMentorById
import com.example.ajarin.domain.mentor.use_cases.SearchMentor
import com.example.ajarin.domain.mentor.use_cases.SearchMentorByCourse
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
    fun provideGetMentorByIdUseCase(): GetMentorById {
        return GetMentorById()
    }

    @Provides
    @ViewModelScoped
    fun provideSearchMentorByCourseUseCase(): SearchMentorByCourse {
        return SearchMentorByCourse()
    }

    @Provides
    @ViewModelScoped
    fun provideSearchMentorUseCase(): SearchMentor {
        return SearchMentor()
    }
}