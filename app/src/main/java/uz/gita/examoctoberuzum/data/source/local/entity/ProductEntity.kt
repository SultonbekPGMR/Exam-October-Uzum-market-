package uz.gita.examoctoberuzum.data.source.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("categoryId"),
        )
    ]
)
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val categoryId: Int,
    val name: String,
    val description: String,
    val oldPrice: String,
    val newPrice: String,
    val image: Int,
    val isDefault: Int = 0,
    val isFavourite: Int = 0,
    val countInCart: Int = 0,
    ) : Serializable
