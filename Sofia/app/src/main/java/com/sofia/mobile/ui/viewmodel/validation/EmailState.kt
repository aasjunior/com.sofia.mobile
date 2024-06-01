package com.sofia.mobile.ui.viewmodel.validation

import android.util.Patterns
import androidx.annotation.StringRes
import com.sofia.mobile.R

class EmailState {
    var email: String = ""
        private set

    @StringRes var error: Int? = null
    var hasTriedToEnterEmail = false
        private set

    fun setEmail(newEmail: String){
        if (newEmail != email) {
            hasTriedToEnterEmail = true
        }
        email = newEmail
        error = if(isValid()) null else R.string.invalid_email
    }
    private fun isValid() : Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}