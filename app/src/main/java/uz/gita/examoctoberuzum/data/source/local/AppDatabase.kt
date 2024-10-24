package uz.gita.examoctoberuzum.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.examoctoberuzum.data.source.local.dao.CategoryDao
import uz.gita.examoctoberuzum.data.source.local.dao.ProductDao
import uz.gita.examoctoberuzum.data.source.local.dao.UserDao
import uz.gita.examoctoberuzum.data.source.local.entity.CategoryEntity
import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity
import uz.gita.examoctoberuzum.data.source.local.entity.UserEntity

@Database(entities = [UserEntity::class, CategoryEntity::class, ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getProductDao():ProductDao


    companion object {
        lateinit var instance: AppDatabase
            private set

        fun init(context: Context) {
            if (!::instance.isInitialized) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "uzum_db")
                    .allowMainThreadQueries()
                    .build()

            }
        }


    }

}