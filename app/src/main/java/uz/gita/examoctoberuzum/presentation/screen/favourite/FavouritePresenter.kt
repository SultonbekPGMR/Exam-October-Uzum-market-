package uz.gita.examoctoberuzum.presentation.screen.favourite

import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity

class FavouritePresenter(private val view: FavouriteContract.View) : FavouriteContract.Presenter {
    private val model by lazy { FavouriteModel() }

    private var list: ArrayList<ProductEntity> = model.getProducts() as ArrayList

    init {
        view.showProducts(list)
        view.showOrHideEmptyBox(list.isEmpty())

    }


    override fun itemFavouriteClicked(pos: Int) {
        list[pos].isFavourite = if (list[pos].isFavourite == 0) 1 else 0
        model.updateProduct(list[pos])
        list.removeAt(pos)
        view.setList(list)
        view.notifyItemRemoved(pos)
        view.showOrHideEmptyBox(list.isEmpty())
    }

    override fun itemCartClicked(pos: Int) {
        list[pos].countInCart++
        model.updateProduct(list[pos])
        view.showToast("Mahsulot savatga qo'shildi. +1")
        view.notifyItemChanged(pos)


    }




}