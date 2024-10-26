package uz.gita.examoctoberuzum.presentation.screen.itemsbycategory

import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity

class ProductsPresenter(private val view: ProductsContract.View, private val categoryId: Int) :
    ProductsContract.Presenter {
    private val model by lazy { ProductsModel() }
    private var list: ArrayList<ProductEntity> =
        model.getProductsByCategoryId(categoryId) as ArrayList

    init {
        view.showProducts(list)
    }

    override fun btnItemClicked(productEntity: ProductEntity) {
//        view.openDetailsScreen(productEntity)
    }

    override fun btnAddProductClicked() {
        view.openNewProductScreen()
    }

    override fun onItemInserted() {
        list.clear()
        list.addAll(model.getProductsByCategoryId(categoryId))
        view.showProducts(list)
    }

    override fun onResume() {
        view.showProducts(model.getProductsByCategoryId(categoryId))
    }

    override fun itemFavouriteClicked(pos: Int) {
        list[pos].isFavourite = if (list[pos].isFavourite == 0) 1 else 0
        model.updateProduct(list[pos])
        view.setList(list)
        view.notifyItemChanged(pos)

    }

    override fun itemCartClicked(pos: Int) {
        list[pos].countInCart++
        model.updateProduct(list[pos])
        view.showToast("Mahsulot savatga qo'shildi. +1")
        view.notifyItemChanged(pos)

    }


}