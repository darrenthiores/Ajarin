package com.example.ajarin.presentation.searchMentor

import com.example.ajarin.domain.core.model.Course
import com.example.ajarin.domain.core.model.allCourses

data class SearchMentorState(
    val searchText: String = "",
    val courses: List<Course> = allCourses,
    val tempCourse: Course? = null,
    val selectedCourse: Course? = null,
    val tempRating: Int = 0,
    val rating: Int = 0,
    val prices: List<String> = dummyPrices,
    val tempPrice: String = "",
    val price: String = "",
    val educations: List<String> = dummyEducations,
    val tempEducation: String = "",
    val education: String = ""
)

private val dummyPrices = listOf(
    "<50k",
    "50k-100k",
    "100k-200k",
    ">200k"
)

val dummyEducations = listOf(
    "SMA 1",
    "SMA 2",
    "SMA 3",
    "S1",
    "S2"
)