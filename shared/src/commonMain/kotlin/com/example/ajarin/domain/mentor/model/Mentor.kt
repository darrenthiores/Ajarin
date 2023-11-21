package com.example.ajarin.domain.mentor.model

import com.example.ajarin.domain.core.model.Course

data class Mentor(
    val id: String,
    val photoUrl: String,
    val name: String,
    val education: String,
    val rating: String,
    val courses: List<Course>,
    val price: String,
    val priceCategory: String
)

val dummyMentors = listOf(
    Mentor(
        id = "M1",
        photoUrl = "https://cdn-icons-png.flaticon.com/512/4202/4202839.png",
        name = "Steven",
        education = "SMA 1",
        rating = "4.5",
        courses = listOf(
            Course(
                id = "7",
                name = "Geografi"
            ),
            Course(
                id = "8",
                name = "Akuntansi"
            )
        ),
        price = "50.000",
        priceCategory = "50k-100k"
    ),
    Mentor(
        id = "M2",
        photoUrl = "https://cdn-icons-png.flaticon.com/512/4202/4202839.png",
        name = "Kevin",
        education = "SMA 2",
        rating = "4.1",
        courses = listOf(
            Course(
                id = "2",
                name = "Biologi"
            ),
            Course(
                id = "3",
                name = "Fisika"
            ),
            Course(
                id = "4",
                name = "Kimia"
            ),
            Course(
                id = "5",
                name = "Matematika"
            )
        ),
        price = "70.000",
        priceCategory = "50k-100k"
    ),
    Mentor(
        id = "M3",
        photoUrl = "https://cdn-icons-png.flaticon.com/512/4202/4202839.png",
        name = "Stevan",
        education = "SMA 1",
        rating = "4.9",
        courses = listOf(
            Course(
                id = "8",
                name = "Akuntansi"
            ),
            Course(
                id = "9",
                name = "Sejarah"
            ),
            Course(
                id = "10",
                name = "Sosiologi"
            )
        ),
        price = "30.000",
        priceCategory = "<50k"
    ),
    Mentor(
        id = "M4",
        photoUrl = "https://cdn-icons-png.flaticon.com/512/4202/4202839.png",
        name = "Darren",
        education = "SMA 3",
        rating = "4.4",
        courses = listOf(
            Course(
                id = "5",
                name = "Matematika"
            ),
            Course(
                id = "6",
                name = "Ekonomi"
            )
        ),
        price = "100.000",
        priceCategory = "50k-100k"
    ),
    Mentor(
        id = "M5",
        photoUrl = "https://cdn-icons-png.flaticon.com/512/4202/4202839.png",
        name = "Hendra",
        education = "SMA 3",
        rating = "4.5",
        courses = listOf(
            Course(
                id = "6",
                name = "Ekonomi"
            ),
            Course(
                id = "7",
                name = "Geografi"
            ),
        ),
        price ="80.000",
        priceCategory = "50k-100k"
    ),
    Mentor(
        id = "M6",
        photoUrl = "https://cdn-icons-png.flaticon.com/512/4202/4202836.png",
        name = "Amanda",
        education = "SMA 1",
        rating = "4.5",
        courses = listOf(
            Course(
                id = "8",
                name = "Akuntansi"
            ),
            Course(
                id = "9",
                name = "Sejarah"
            )
        ),
        price ="55.000",
        priceCategory = "50k-100k"
    ),
    Mentor(
        id = "M7",
        photoUrl = "https://cdn-icons-png.flaticon.com/512/4202/4202836.png",
        name = "Selena",
        education = "S1",
        rating = "4.9",
        courses = listOf(
            Course(
                id = "8",
                name = "Akuntansi"
            ),
            Course(
                id = "5",
                name = "Matematika"
            )
        ),
        price = "70.000",
        priceCategory = "50k-100k"
    ),
    Mentor(
        id = "M8",
        photoUrl = "https://cdn-icons-png.flaticon.com/512/4202/4202839.png",
        name = "Smith",
        education = "S2",
        rating = "5.0",
        courses = listOf(
            Course(
                id = "3",
                name = "Fisika"
            ),
            Course(
                id = "1",
                name = "B. Indonesia"
            )
        ),
        price = "75.000",
        priceCategory = "50k-100k"
    ),
    Mentor(
        id = "M9",
        photoUrl = "https://cdn-icons-png.flaticon.com/512/4202/4202836.png",
        name = "Yuli",
        education = "SMA 2",
        rating = "4.8",
        courses = listOf(
            Course(
                id = "9",
                name = "Sejarah"
            ),
            Course(
                id = "10",
                name = "Sosiologi"
            )
        ),
        price ="65.000",
        priceCategory = "50k-100k"
    ),
    Mentor(
        id = "M10",
        photoUrl = "https://cdn-icons-png.flaticon.com/512/4202/4202839.png",
        name = "Jaden",
        education = "SMA 3",
        rating = "4.8",
        courses = listOf(
            Course(
                id = "2",
                name = "Biologi"
            ),
            Course(
                id = "3",
                name = "FIsika"
            )
        ),
        price ="75.000",
        priceCategory = "50k-100k"
    ),
    Mentor(
        id = "M11",
        photoUrl = "https://cdn-icons-png.flaticon.com/512/4202/4202839.png",
        name = "Aldi",
        education = "SMA 1",
        rating = "4.5",
        courses = listOf(
            Course(
                id = "8",
                name = "Akuntansi"
            ),
            Course(
                id = "9",
                name = "Sejarah"
            )
        ),
        price ="55.000",
        priceCategory = "50k-100k"
    ),
    Mentor(
        id = "M12",
        photoUrl = "https://cdn-icons-png.flaticon.com/512/4202/4202839.png",
        name = "Tommy",
        education = "SMA 3",
        rating = "4.8",
        courses = listOf(
            Course(
                id = "4",
                name = "Kimia"
            ),
            Course(
                id = "5",
                name = "Matematika"
            )
        ),
        price ="55.000",
        priceCategory = "50k-100k"
    )
)