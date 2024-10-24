package uz.gita.examoctoberuzum.presentation.screen.catalog

import uz.gita.examoctoberuzum.data.source.local.entity.CategoryEntity

interface CategoryContract {

    interface Model{

        fun getAllCategories():List<CategoryEntity>

    }


    interface View{

        fun showCategories(list:List<CategoryEntity>)
        fun openNewCategoryScreen()
        fun openItemsByCategoryScreen(categoryEntity: CategoryEntity)

    }

    interface Presenter{
        fun btnItemClicked(categoryEntity: CategoryEntity)
        fun btnPlusClicked()
        fun onResume()

    }

}