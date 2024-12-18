package uz.gita.examoctoberuzum.presentation.screen.cart

import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity
import uz.gita.examoctoberuzum.util.formatPrice

class CartPresenter(private val view: CartContract.View) : CartContract.Presenter {
    private val model by lazy { CartModel() }
    private var list: ArrayList<ProductEntity> = model.getProducts()
    private var totalPrice = ""

    init {

        view.showProducts(list)

        setTitleText()
        totalPrice = calculateTotalPrice()
        view.setTextToToTalPrice(totalPrice)
        view.setTextToProductCount(list.size.toString())
    }

    private fun setTitleText() {
        if (list.isEmpty()) {
            view.setTextToTitle("Savatda mahsulot yo'q")
            view.disableConfirmBtn()
        } else {
            view.setTextToTitle("Savatda ${list.size} ta mahsulot")
            view.enableConfirmBtn()
        }
    }

    private fun calculateTotalPrice(): String {
        var sum = 0
        list.forEach {
            sum += it.newPrice.toInt() * it.countInCart
        }
        return sum.toString().formatPrice()
    }

    override fun btnConfirmClicked() {
        for (i in 0 until list.size) {
            list[i].countInCart = 0
            model.updateProduct(list[i])
        }
        list.clear()
        view.showProducts(list)
        view.disableConfirmBtn()

        view.setTextToToTalPrice(calculateTotalPrice())
        view.setTextToProductCount("${list.size}")
        setTitleText()
        view.showToast("Muvafaqqiyatli  sotib olindi")

    }

    override fun decrementClicked(pos: Int, productEntity: ProductEntity) {
        list[pos].countInCart--
        list[pos].totalPrice = (list[pos].newPrice.toInt() * list[pos].countInCart).toString()
        model.updateProduct(list[pos])
        if (list[pos].countInCart < 1) {
            list.removeAt(pos)
            view.setList(list)
            view.notifyItemDeleted(pos)
        } else {
            view.notifyItemChanged(pos)
        }
        view.setTextToToTalPrice(calculateTotalPrice())
        view.setTextToProductCount("${list.size}")
        setTitleText()

    }

    override fun incrementClicked(pos: Int, productEntity: ProductEntity) {
        list[pos].countInCart++
        list[pos].totalPrice = (list[pos].newPrice.toInt() * list[pos].countInCart).toString()
        model.updateProduct(list[pos])
        view.setList(list)
        view.notifyItemChanged(pos)
        view.setTextToToTalPrice(calculateTotalPrice())
        view.setTextToProductCount("${list.size}")
        setTitleText()
    }

    override fun itemClicked(productEntity: ProductEntity) {

    }

    override fun favouriteClicked(pos: Int) {
        list[pos].isFavourite = if (list[pos].isFavourite == 0) 1 else 0
        model.updateProduct(list[pos])
        view.setList(list)
        view.notifyItemChanged(pos)

    }

    override fun deleteClicked(pos: Int) {
        list[pos].countInCart = 0
        model.updateProduct(list[pos])
        list.removeAt(pos)
        view.setList(list)
        view.notifyItemDeleted(pos)
        view.setTextToToTalPrice(calculateTotalPrice())
        view.setTextToProductCount("${list.size}")
        setTitleText()

    }

}