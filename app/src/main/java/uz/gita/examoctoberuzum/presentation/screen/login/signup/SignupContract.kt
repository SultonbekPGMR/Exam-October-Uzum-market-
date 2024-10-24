package uz.gita.examoctoberuzum.presentation.screen.login.signup

import uz.gita.examoctoberuzum.data.source.local.entity.UserEntity

interface SignupContract {


    interface Model {
        fun isUserExists(phoneNumber: String): Boolean
        fun insertUser(userEntity: UserEntity)


    }

    interface View {
        fun showToast(message: String)
        fun openLoginScreen()
        fun openLogInScreenAfterRegister()

    }

    interface Presenter {
        fun btnSignUpClicked(name: String, age: String, phoneNumber: String, password1: String, password2: String)
        fun btnSignInClicked()

    }


}