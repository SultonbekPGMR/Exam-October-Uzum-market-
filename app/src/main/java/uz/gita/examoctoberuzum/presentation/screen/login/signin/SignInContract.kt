package uz.gita.examoctoberuzum.presentation.screen.login.signin

import uz.gita.examoctoberuzum.data.source.local.entity.UserEntity

interface SignInContract {


    interface Model {
        fun isUserExists(phoneNumber:String):Boolean
        fun getUserByData(phoneNumber: String): UserEntity

    }

    interface View {

        fun showToast(message: String)
        fun openRegisterScreen()
        fun openMainScreen()

    }

    interface Presenter {
        fun btnSignInClicked(phoneNumber: String, password: String)
        fun btnRegisterClicked()

    }


}