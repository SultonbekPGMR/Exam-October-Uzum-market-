package uz.gita.examoctoberuzum.presentation.screen.itemsbycategory

import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity

interface ProductsContract {

    interface Model {
        fun getProductsByCategoryId(id:Int): List<ProductEntity>
        fun updateProduct(productEntity: ProductEntity)

    }

    interface View {
        fun showProducts(list:List<ProductEntity>)
        fun openNewProductScreen()
        fun openDetailsScreen(productEntity: ProductEntity)
        fun setList(pos: Int, productsByCategoryId: List<ProductEntity>)

    }

    interface Presenter{

        fun btnItemClicked(productEntity: ProductEntity)
        fun btnAddProductClicked()
        fun onItemInserted()
        fun onResume()
         fun favouriteClicked(pos: Int, productEntity: ProductEntity)

    }

}