package com.example.hackathon_perevalfstr

import com.example.hackathon_perevalfstr.view.fragments.UserProfileFragment

class UserConstructor(private val fragment: UserProfileFragment) {

    var email: CharSequence? = null
        set(value) {
            field = value
            isEmail = !value.isNullOrEmpty()
        }
    var patronymic: CharSequence? = null
        set(value) {
            field = value
            isPatronymic = !value.isNullOrEmpty()
        }
    var firstName: CharSequence? = null
        set(value) {
            field = value
            isFirstName = !value.isNullOrEmpty()
        }
    var lastName: CharSequence? = null
        set(value) {
            field = value
            isLastName = !value.isNullOrEmpty()
        }

    private var isLastName = false
        set(value) {
            if (value != field) fragment.lastNameStateChanged(value)
            field = value
            if (value && userIsReady()) fragment.onUserIsReady()
        }

    private var isFirstName = false
        set(value) {
            if (value != field) fragment.firstNameStateChanged(value)
            field = value
            if (value && userIsReady()) fragment.onUserIsReady()
        }

    private var isPatronymic = false
        set(value) {
            if (value != field) fragment.patronymicStateChanged(value)
            field = value
            if (value && userIsReady()) fragment.onUserIsReady()
        }

    private var isEmail = false
        set(value) {
            if (value != field) fragment.emailStateChanged(value)
            field = value
            if (value && userIsReady()) fragment.onUserIsReady()
        }

    private fun userIsReady() = isLastName && isFirstName && isPatronymic && isEmail
}