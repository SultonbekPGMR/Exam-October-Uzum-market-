package uz.gita.examoctoberuzum.presentation.screen.login.signup

import uz.gita.examoctoberuzum.data.source.local.AppDatabase
import uz.gita.examoctoberuzum.data.source.local.entity.UserEntity

class SignUpModel : SignupContract.Model {

    private val userDao by lazy { AppDatabase.instance.getUserDao() }

    override fun isUserExists(phoneNumber: String): Boolean {
        return userDao.isUserExist(phoneNumber)
    }

    override fun insertUser(userEntity: UserEntity) {
        userDao.insertUser(userEntity)

    }



}