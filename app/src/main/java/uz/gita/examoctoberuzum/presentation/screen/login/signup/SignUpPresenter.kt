package uz.gita.examoctoberuzum.presentation.screen.login.signup

import uz.gita.examoctoberuzum.data.source.local.entity.UserEntity
import uz.gita.examoctoberuzum.data.source.preference.Preferences

class SignUpPresenter(private val view: SignupContract.View) : SignupContract.Presenter {
    private val model by lazy { SignUpModel() }
    override fun btnSignUpClicked(
        name: String,
        age: String,
        phoneNumber: String,
        password1: String,
        password2: String,
    ) {
        if (name.isEmpty() || age.isEmpty() || phoneNumber.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
            view.showToast("To'ldiring!")
            return
        }

        if (password1 != password2) {
            view.showToast("Parollar mos emas!")
            return
        }

        if (model.isUserExists(phoneNumber)) {
            view.showToast("Bu raqam oldin ro'yxatdan o'tgan!")
            return
        }


        // everything correct
        model.insertUser(
            UserEntity(
                0, fullName = name, age = age, phoneNumber = phoneNumber, password1
            )
        )

        view.openLogInScreenAfterRegister()


    }


    override fun btnSignInClicked() {
        view.openLoginScreen()
    }

}