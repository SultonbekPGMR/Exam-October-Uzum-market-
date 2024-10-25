package uz.gita.examoctoberuzum.presentation.screen.itemsbycategory

import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity

class ProductsPresenter(private val view: ProductsContract.View, private val categoryId: Int) :
    ProductsContract.Presenter {
    private val model by lazy { ProductsModel() }

    init {
        view.showProducts(model.getProductsByCategoryId(categoryId))
    }

    override fun btnItemClicked(productEntity: ProductEntity) {
        view.openDetailsScreen(productEntity)
    }

    override fun btnAddProductClicked() {
        view.openNewProductScreen()
    }

    override fun onItemInserted() {
        view.showProducts(model.getProductsByCategoryId(categoryId))
    }

    override fun onResume() {
        view.showProducts(model.getProductsByCategoryId(categoryId))
    }

    override fun favouriteClicked(pos: Int, productEntity: ProductEntity) {
        productEntity.isFavourite = if (productEntity.isFavourite == 0) 1 else 0
        model.updateProduct(productEntity)

        view.setList(pos, model.getProductsByCategoryId(categoryId))

    }
}