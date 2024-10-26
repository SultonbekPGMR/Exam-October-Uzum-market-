package uz.gita.examoctoberuzum.presentation.screen.itemsbycategory

import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity

interface ProductsContract {

    interface Model {
        fun getProductsByCategoryId(id: Int): List<ProductEntity>
        fun updateProduct(productEntity: ProductEntity)

    }

    interface View {
        fun showProducts(list: List<ProductEntity>)
        fun openNewProductScreen()
        fun openDetailsScreen(productEntity: ProductEntity)
        fun setList(list: List<ProductEntity>)
        fun notifyItemChanged(pos: Int)
        fun showToast(message: String)
    }

    interface Presenter {

        fun btnItemClicked(productEntity: ProductEntity)
        fun btnAddProductClicked()
        fun itemFavouriteClicked(pos: Int)
        fun itemCartClicked(pos: Int)
        fun onItemInserted()
        fun onResume()

    }

}