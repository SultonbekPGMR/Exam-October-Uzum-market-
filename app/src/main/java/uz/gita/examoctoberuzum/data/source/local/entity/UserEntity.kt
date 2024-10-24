package uz.gita.examoctoberuzum.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val fullName: String,
    val age: String,
    val phoneNumber: String,
    val password: String,
    )