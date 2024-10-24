package uz.gita.examoctoberuzum.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductEntity")
    fun getAllProducts():List<ProductEntity>

    @Query("SELECT * FROM ProductEntity WHERE categoryId = :id")
    fun getProductsByCategoryId(id:Int):List<ProductEntity>

    @Insert
    fun insertProduct(productEntity: ProductEntity)


}