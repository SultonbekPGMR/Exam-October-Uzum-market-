package uz.gita.examoctoberuzum.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductEntity")
    fun getAllProducts(): List<ProductEntity>

    @Query("SELECT * FROM ProductEntity WHERE categoryId = :id")
    fun getProductsByCategoryId(id: Int): List<ProductEntity>

    @Query("SELECT * FROM ProductEntity WHERE isFavourite = 1")
    fun getFavouriteProducts(): List<ProductEntity>

    @Query("SELECT * FROM ProductEntity WHERE countInCart > 0")
    fun getCartProducts(): List<ProductEntity>

    @Query("SELECT * FROM ProductEntity WHERE ProductEntity.name LIKE  :text || '%'")
    fun getProductsByQuery(text: String): List<ProductEntity>


    @Insert
    fun insertProduct(productEntity: ProductEntity)

    @Update
    fun updateProduct(productEntity: ProductEntity)


}