package uz.gita.examoctoberuzum.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity()
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val image: Int = 0,
):Serializable