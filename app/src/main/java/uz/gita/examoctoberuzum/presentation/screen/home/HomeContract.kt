package uz.gita.examoctoberuzum.presentation.screen.home

import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity

class HomeContract {

    interface Model {
        fun getProducts(): List<ProductEntity>
        fun updateProduct(productEntity: ProductEntity)
    }

    interface View {
        fun showProducts(list: List<ProductEntity>)
        fun setList(list: List<ProductEntity>)
        fun notifyItemChanged(pos:Int)
        fun openSearchScreen()
        fun closeSearchScreen()
        fun openFavouriteScreen()
        fun showToast(message:String)
        fun sendQueryToSearchScreen(query: String)


    }

    interface Presenter{

        fun itemFavouriteClicked(pos: Int)
        fun itemCartClicked(pos: Int)
        fun btnFavouriteClicked()
        fun searchQueryFocusChanged(boolean: Boolean)
        fun searchQueryTextChanged(query:String)


    }


}