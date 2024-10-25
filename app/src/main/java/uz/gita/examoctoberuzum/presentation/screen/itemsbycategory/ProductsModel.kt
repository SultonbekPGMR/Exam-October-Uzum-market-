package uz.gita.examoctoberuzum.presentation.screen.itemsbycategory

import uz.gita.examoctoberuzum.data.source.local.AppDatabase
import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity

class ProductsModel : ProductsContract.Model {
    private val productDao by lazy { AppDatabase.instance.getProductDao() }
    override fun getProductsByCategoryId(id: Int): List<ProductEntity> {
        return productDao.getProductsByCategoryId(id = id)
    }

    override fun updateProduct(productEntity: ProductEntity) {
        productDao.updateProduct(productEntity)
    }
}