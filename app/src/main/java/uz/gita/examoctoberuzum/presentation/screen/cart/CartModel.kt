package uz.gita.examoctoberuzum.presentation.screen.cart

import uz.gita.examoctoberuzum.data.source.local.AppDatabase
import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity

class CartModel : CartContract.Model {
    private val productDao by lazy { AppDatabase.instance.getProductDao() }

    override fun getProducts(): ArrayList<ProductEntity> {
        return productDao.getCartProducts() as ArrayList<ProductEntity>
    }

    override fun updateProduct(productEntity: ProductEntity) {
        productDao.updateProduct(productEntity)
    }
}