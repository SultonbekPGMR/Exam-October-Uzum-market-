package uz.gita.examoctoberuzum.presentation.screen.favourite

import uz.gita.examoctoberuzum.data.source.local.AppDatabase
import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity
import uz.gita.examoctoberuzum.presentation.screen.itemsbycategory.ProductsModel

class FavouriteModel:FavouriteContract.Model {
    private val productDao by lazy { AppDatabase.instance.getProductDao() }

    override fun getProducts(): List<ProductEntity> {
        return productDao.getFavouriteProducts()
    }

    override fun updateProduct(productEntity: ProductEntity) {
        productDao.updateProduct(productEntity)
    }


}