package com.example.hackathon_perevalfstr

import android.text.InputType

data class User(
    val lastName: String,
    val firstName: String,
    val patronymic: String?,
    val email: String,
    val phone: String?,
    val anotherLink: String?
) {
}
