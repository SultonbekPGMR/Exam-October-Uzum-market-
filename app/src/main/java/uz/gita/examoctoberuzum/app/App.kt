package uz.gita.examoctoberuzum.app

import android.app.Application
import uz.gita.examoctoberuzum.data.source.local.AppDatabase
import uz.gita.examoctoberuzum.data.source.preference.Preferences
import uz.gita.examoctoberuzum.util.DataLoader

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Preferences.init(getSharedPreferences("shared", MODE_PRIVATE))
        AppDatabase.init(this)
        if (Preferences.isFirstTime()) {
            DataLoader.upLoadDefaultData()
            Preferences.isFirstTime(false)
        }


    }

}