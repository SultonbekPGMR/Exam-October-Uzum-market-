package uz.gita.examoctoberuzum.presentation.screen.home

import uz.gita.examoctoberuzum.data.source.local.AppDatabase
import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity

class HomeModel :HomeContract.Model{

    private val productDao by lazy { AppDatabase.instance.getProductDao() }

    override fun getProducts(): List<ProductEntity>  = productDao.getAllProducts()

    override fun updateProduct(productEntity: ProductEntity) = productDao.updateProduct(productEntity)


}