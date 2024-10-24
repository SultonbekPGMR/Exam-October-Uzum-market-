package uz.gita.examoctoberuzum.presentation.screen.login.signin

import uz.gita.examoctoberuzum.data.source.preference.Preferences

class SignInPresenter(private val view: SignInContract.View) : SignInContract.Presenter {
    private val model by lazy { SignInModel() }
    override fun btnSignInClicked(phoneNumber: String, password: String) {
        if (phoneNumber.isEmpty() || password.isEmpty()) {
            view.showToast("To'ldiring!")
            return
        }
        if (!model.isUserExists(phoneNumber)) {
            view.showToast("Bu raqamdagi akkaunt topilmadi. Ro'yxatdan o'ting!")
            return
        }

        if (model.getUserByData(phoneNumber).password != password) {
            view.showToast("Parol xato!")
            return
        }

        // everything correct
        Preferences.saveCurrentUserPhoneNumber(phoneNumber)
        Preferences.isUserSignedIn(true)

        view.openMainScreen()


    }

    override fun btnRegisterClicked() {
        view.openRegisterScreen()
    }

}