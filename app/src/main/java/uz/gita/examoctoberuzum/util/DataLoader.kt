package uz.gita.examoctoberuzum.util

import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.AppDatabase
import uz.gita.examoctoberuzum.data.source.local.entity.CategoryEntity
import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity

object DataLoader {
    private val categories by lazy { ArrayList<CategoryEntity>() }
    private val products by lazy { ArrayList<ProductEntity>() }

    private val categoryDao by lazy { AppDatabase.instance.getCategoryDao() }
    private val productDao by lazy { AppDatabase.instance.getProductDao() }

    fun upLoadDefaultData() {


        submitCategories()

        submitProducts()

    }

    private fun submitProducts() {
        products.add(
            ProductEntity(
                id = 0,
                categoryId = 50,
                description = "zo'r",
                name = "Televizor",
                oldPrice = "180000",
                newPrice = "120000",
                totalPrice = "120000",
                image = R.drawable.def_product_1,
                isDefault = 1
            )
        )
        products.add(
            ProductEntity(
                id = 0,
                categoryId = 40,
                description = "zo'r kiyim",
                name = "Erkaklar Hudisi",
                oldPrice = "150000",
                newPrice = "100000",
                totalPrice = "100000",
                image = R.drawable.def_product_2,
                isDefault = 1
            )
        )
        products.add(
            ProductEntity(
                id = 0,
                categoryId = 50,
                description = "zo'r uskuna",
                name = "mini Pilisos",
                oldPrice = "80000",
                newPrice = "30000",
                totalPrice = "30000",
                image = R.drawable.def_product_3,
                isDefault = 1
            )
        )
        products.add(
            ProductEntity(
                id = 0,
                categoryId = 30,
                description = "zo'r yog'",
                name = "Kungaboqar Yog'i",
                oldPrice = "26000",
                newPrice = "19000",
                totalPrice = "19000",
                image = R.drawable.def_product_4,
                isDefault = 1
            )
        )
        products.add(
            ProductEntity(
                id = 0,
                categoryId = 40,
                description = "zo'r parashok",
                name = "Parashok",
                oldPrice = "67000",
                newPrice = "41000",
                totalPrice = "41000",
                image = R.drawable.def_product_5,
                isDefault = 1
            )
        )
        products.add(
            ProductEntity(
                id = 0,
                categoryId = 50,
                description = "zo'r narsa",
                name = "Havo xushbo'ylartirgich",
                oldPrice = "87000",
                newPrice = "51000",
                totalPrice = "51000",
                image = R.drawable.def_product_6,
                isDefault = 1
            )
        )
        products.add(
            ProductEntity(
                id = 0,
                categoryId = 50,
                description = "zo'r naushnik",
                name = "Naushnik",
                oldPrice = "87000",
                newPrice = "51000",
                totalPrice = "51000",
                image = R.drawable.def_product_7,
                isDefault = 1
            )
        )

        products.forEach {
            productDao.insertProduct(it)
        }

    }


    private fun submitCategories() {
        categories.add(CategoryEntity(10, "Sumkalar", R.drawable.cat_bag))
        categories.add(CategoryEntity(20, "Kitoblar", R.drawable.cat_book))
        categories.add(CategoryEntity(30, "Oziq Ovqatlar", R.drawable.cat_food))
        categories.add(CategoryEntity(40, "Kiyimlar", R.drawable.cat_clothes))
        categories.add(CategoryEntity(50, "Texnikalar", R.drawable.cat_television))
        categories.add(CategoryEntity(60, "Qo'l soatlar", R.drawable.cat_clock))

        categories.forEach {
            categoryDao.insertCategory(it)
        }

    }


}