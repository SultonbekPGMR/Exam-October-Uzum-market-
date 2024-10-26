package uz.gita.examoctoberuzum.presentation.screen.home

class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter {

    private val model by lazy { HomeModel() }
    private val list by lazy { model.getProducts() as ArrayList }

    init {
        view.showProducts(list)
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


    override fun btnFavouriteClicked() {
        view.openFavouriteScreen()
    }

    override fun searchQueryFocusChanged(boolean: Boolean) {

        if (boolean) {
            view.openSearchScreen()
        } else {
            view.closeSearchScreen()
            list.clear()
            list.addAll(model.getProducts())
            view.showProducts(list)
        }

    }

    override fun searchQueryTextChanged(query: String) {

        view.sendQueryToSearchScreen(query)

    }
}