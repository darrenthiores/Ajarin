package com.example.ajarin.presentation.profile

import com.example.ajarin.domain.user.model.User

data class ProfileState(
    val user: User? = null,
    val isFetchingUser: Boolean = false,
    val isDialogShow: Boolean = false
)

val dummyUsers = listOf(
    User(
        id = "U1",
        name = "Darren Thiores",
        email = "darren.thiores@binus.ac.id",
        number = "+6282282512065",
        roleType = "2"
    ),
    User(
        id = "U2",
        name = "Kevin Valentino",
        email = "kevin.valentino@binus.ac.id",
        number = "+6282282512065",
        roleType = "2"
    ),
    User(
        id = "U3",
        name = "Steven Roland Enceil",
        email = "steven.enceil@binus.ac.id",
        number = "+6282282512065",
        roleType = "1"
    ),
    User(
        id = "U4",
        name = "Stevan Wijaya",
        email = "stevan.wijaya@binus.ac.id",
        number = "+6282282512065",
        roleType = "1"
    )
)