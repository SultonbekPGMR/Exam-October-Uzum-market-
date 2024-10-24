package uz.gita.examoctoberuzum.presentation.screen.login.signin

import uz.gita.examoctoberuzum.data.source.local.AppDatabase
import uz.gita.examoctoberuzum.data.source.local.entity.UserEntity

class SignInModel : SignInContract.Model {

    private val userDao by lazy { AppDatabase.instance.getUserDao() }

    override fun isUserExists(phoneNumber: String): Boolean {
        return userDao.isUserExist(phoneNumber)
    }

    override fun getUserByData(phoneNumber: String): UserEntity {
        return userDao.getUserByPhoneNumber(phoneNumber)!!
    }


}