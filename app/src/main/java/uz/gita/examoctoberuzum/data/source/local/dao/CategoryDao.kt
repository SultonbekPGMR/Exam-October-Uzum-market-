package uz.gita.examoctoberuzum.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.gita.examoctoberuzum.data.source.local.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Query("SELECT * FROM CategoryEntity")
    fun getAllCategories(): List<CategoryEntity>

    @Insert
    fun insertCategory(categoryEntity: CategoryEntity)


}