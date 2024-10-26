package uz.gita.examoctoberuzum.presentation.screen.favourite

import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity

interface FavouriteContract {

    interface Model {

        fun getProducts(): List<ProductEntity>
        fun updateProduct(productEntity: ProductEntity)


    }

    interface View {
        fun showProducts(list: List<ProductEntity>)
        fun setList(list: List<ProductEntity>)
        fun notifyItemChanged(pos: Int)
        fun notifyItemRemoved(pos: Int)
        fun setList(pos: Int, list: List<ProductEntity>)
        fun showToast(message: String)

        fun showOrHideEmptyBox(boolean: Boolean)

    }


    interface Presenter {
        fun itemFavouriteClicked(pos: Int)
        fun itemCartClicked(pos: Int)
    }


}