package uz.gita.examoctoberuzum.presentation.screen.catalog

import uz.gita.examoctoberuzum.data.source.local.AppDatabase
import uz.gita.examoctoberuzum.data.source.local.entity.CategoryEntity

class CategoryModel : CategoryContract.Model {

    private val categoryDao by lazy { AppDatabase.instance.getCategoryDao() }

    override fun getAllCategories(): List<CategoryEntity> {
        return categoryDao.getAllCategories()
    }
}