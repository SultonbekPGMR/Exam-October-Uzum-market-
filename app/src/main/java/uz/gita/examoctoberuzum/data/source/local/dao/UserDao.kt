package uz.gita.examoctoberuzum.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.gita.examoctoberuzum.data.source.local.entity.UserEntity

@Dao
interface UserDao {
    @Query(
        "SELECT EXISTS(SELECT 1 FROM UserEntity WHERE phoneNumber = :phoneNumber)"
    )
    fun isUserExist(
        phoneNumber: String
    ): Boolean

    @Query(
        "SELECT * FROM UserEntity WHERE phoneNumber = :phoneNumber"
    )
    fun getUserByPhoneNumber(
        phoneNumber: String
    ): UserEntity?


    @Insert
    fun insertUser(userEntity: UserEntity)

    @Update
    fun updateUser(userEntity: UserEntity)

}
