package uz.gita.examoctoberuzum.data.source.preference

import android.content.SharedPreferences

object Preferences {

    private lateinit var preferences: SharedPreferences

    private const val SIGN_IN = "is_in"
    private const val USER_PHONE_NUMBER = "user_number"

    fun init(sharedPreferences: SharedPreferences) {
        preferences = sharedPreferences
    }


    fun isUserSignedIn(): Boolean {
        return preferences.getBoolean(SIGN_IN, false)
    }

    fun isUserSignedIn(boolean: Boolean) {
        preferences.edit().putBoolean(SIGN_IN, boolean).apply()
    }

    fun saveCurrentUserPhoneNumber(phoneNumber: String) {
        preferences.edit().putString(USER_PHONE_NUMBER, phoneNumber).apply()
    }

    fun getCurrentUserPhoneNumber(): String {
        return preferences.getString(USER_PHONE_NUMBER, "null") ?: "null"
    }


}