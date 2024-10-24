package uz.gita.examoctoberuzum.presentation.screen.catalog

import uz.gita.examoctoberuzum.data.source.local.entity.CategoryEntity

class CategoryPresenter(private val view: CategoryContract.View) : CategoryContract.Presenter {

    private val model by lazy { CategoryModel() }
    init {
        view.showCategories(model.getAllCategories())
    }
    override fun btnItemClicked(categoryEntity: CategoryEntity) {

    }

    override fun btnPlusClicked() {
        view.openNewCategoryScreen()
    }

    override fun onResume() {
        view.showCategories(model.getAllCategories())
    }


}