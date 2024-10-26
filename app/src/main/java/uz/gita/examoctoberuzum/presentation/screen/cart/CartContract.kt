package uz.gita.examoctoberuzum.presentation.screen.cart

import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity

interface CartContract {

    interface Model {
        fun getProducts(): ArrayList<ProductEntity>

        fun updateProduct(productEntity: ProductEntity)


    }

    interface View {
        fun showProducts(list: List<ProductEntity>)

        fun showToast(message: String)
        fun setTextToTitle(text: String)
        fun setTextToToTalPrice(text: String)
        fun setTextToProductCount(text: String)
        fun enableConfirmBtn()
        fun disableConfirmBtn()
        fun setList(list: List<ProductEntity>)
        fun notifyItemChanged(pos: Int)
        fun notifyItemDeleted(pos: Int)
        fun openDetailsScreen()


    }


    interface Presenter {

        fun btnConfirmClicked()

        fun decrementClicked(pos: Int, productEntity: ProductEntity)
        fun incrementClicked(pos: Int, productEntity: ProductEntity)

        fun itemClicked(productEntity: ProductEntity)

        fun favouriteClicked(pos: Int)

        fun deleteClicked(pos: Int)


    }


}