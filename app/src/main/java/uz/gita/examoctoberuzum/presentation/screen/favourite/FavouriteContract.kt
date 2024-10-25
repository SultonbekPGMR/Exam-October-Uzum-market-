package uz.gita.examoctoberuzum.presentation.screen.favourite

import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity
import uz.gita.examoctoberuzum.presentation.screen.itemsbycategory.ProductsModel

interface FavouriteContract {

    interface Model {

        fun getProducts(): List<ProductEntity>
        fun updateProduct(productEntity: ProductEntity)


    }

    interface View {

        fun showProducts(list: List<ProductEntity>)
        fun setList(pos: Int, list: List<ProductEntity>)

    }


    interface Presenter {
        fun itemClicked(productEntity: ProductEntity)
        fun btnFavouriteClicked(pos: Int, productEntity: ProductEntity)
        fun onResume()
    }


}