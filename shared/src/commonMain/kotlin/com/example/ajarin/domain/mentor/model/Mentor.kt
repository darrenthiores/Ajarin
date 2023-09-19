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
        photoUrl = "https://api.time.com/wp-content/uploads/2017/12/terry-crews-person-of-year-2017-time-magazine-2.jpg?quality=85&w=1600",
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
        photoUrl = "https://img.resized.co/breaking-news/eyJkYXRhIjoie1widXJsXCI6XCJodHRwczpcXFwvXFxcL2ltYWdlLmFzc2V0cy5wcmVzc2Fzc29jaWF0aW9uLmlvXFxcL3YyXFxcL2ltYWdlXFxcL3Byb2R1Y3Rpb25cXFwvNWI4MjEzODg4MmI4NjMxYmFiYTYyMGM4MzYyZmYwMzFZMjl1ZEdWdWRITmxZWEpqYUdGd2FTd3hOamMzT0RJeU1ESTFcXFwvMi43MDA3NjY1OC5qcGc_dz02NDBcIixcIndpZHRoXCI6XCI2NDBcIixcImhlaWdodFwiOlwiOTYwXCIsXCJkZWZhdWx0XCI6XCJodHRwczpcXFwvXFxcL3d3dy5icmVha2luZ25ld3MuaWVcXFwvaW1hZ2VzXFxcL25vLWltYWdlLnBuZ1wiLFwib3B0aW9uc1wiOntcIm91dHB1dFwiOlwid2VicFwifX0iLCJoYXNoIjoiNmExMWI0YjZhNmQ1MWE3MmNiYmY2NmNkZTQ1ZjE4YzQxY2M4MzA3NCJ9/will-smith-makes-first-in-person-awards-show-appearance-since-oscars-slap.jpg?w=640",
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
        photoUrl = "https://m0.her.ie/wp-content/uploads/2018/01/07093633/GettyImages-887815620.jpg",
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
        photoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsy3zU6gHCYIgHQ1hKv1ASWQ62U_Jpe3Wdfg&usqp=CAU",
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
        photoUrl = "https://media.vanityfair.com/photos/6319eab06009e759e6638e28/master/w_2560%2Cc_limit/1421315651",
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
        photoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTHPT1r2oYcOXzvd8fR5cvNmBTqV-Brnr9apw&usqp=CAU",
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
        photoUrl = "https://cdn-2.tstatic.net/banten/foto/bank/images/wanita-beruntung-menurut-zodiak.jpg",
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
        photoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCp_ByMCZW8m0s3KmAbIENDvR2Zc_HkBJyYw&usqp=CAU",
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
        photoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQa1pJgqNGQ7G0o2oaT3CLntytr0M2I8BlyCA&usqp=CAU",
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
        photoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQJcanSBRQEGcqNjU3JR-CGLpxgFD1e6UZQ4w&usqp=CAU",
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
        photoUrl = "https://images.unsplash.com/flagged/photo-1570612861542-284f4c12e75f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8cGVyc29ufGVufDB8fDB8fHww&w=1000&q=80",
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
        photoUrl = "https://static.toiimg.com/photo/89456086.cms",
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