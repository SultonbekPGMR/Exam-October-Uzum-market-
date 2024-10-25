package uz.gita.examoctoberuzum.presentation.screen.favourite

import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity

class FavouritePresenter(private val view: FavouriteContract.View) : FavouriteContract.Presenter {
    private val model by lazy { FavouriteModel() }

    private lateinit var list: ArrayList<ProductEntity>

    init {
        list = model.getProducts() as ArrayList
        view.showProducts(list)
    }

    override fun itemClicked(productEntity: ProductEntity) {
    }

    override fun btnFavouriteClicked(pos: Int, productEntity: ProductEntity) {
        productEntity.isFavourite = 0
        model.updateProduct(productEntity)
        list.removeAt(pos)
        view.setList(pos, list)

    }

    override fun onResume() {
        list = model.getProducts() as ArrayList<ProductEntity>
    }
}