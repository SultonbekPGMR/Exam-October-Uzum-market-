package uz.gita.examoctoberuzum.presentation.screen.profile

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.AppDatabase
import uz.gita.examoctoberuzum.data.source.local.entity.UserEntity
import uz.gita.examoctoberuzum.data.source.preference.Preferences
import uz.gita.examoctoberuzum.databinding.ScreenProfileBinding
import uz.gita.examoctoberuzum.presentation.screen.main.MainScreenDirections
import uz.gita.examoctoberuzum.util.navigateTo

class ProfileScreen : Fragment(R.layout.screen_profile) {

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var _binding: ScreenProfileBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = ScreenProfileBinding.bind(view)


        val user: UserEntity = AppDatabase.instance.getUserDao().getUserByPhoneNumber(Preferences.getCurrentUserPhoneNumber())!!

        binding.name.text = user.fullName
        binding.phoneNumber.text = user.phoneNumber


        binding.btnEdit.setOnClickListener {

            navigateTo(MainScreenDirections.actionMainScreenToEditProfile())


        }


        binding.btnLogOut.setOnClickListener {
            Preferences.isUserSignedIn(false)
            navigateTo(MainScreenDirections.actionMainScreenToSignInScreen())
        }


        binding.apply {
            buyurma.setOnClickListener { showToast("buyurmalarim") }
            sharh.setOnClickListener { showToast("Sharhlarim") }
            nasiya.setOnClickListener { showToast("Nasiya savdo") }
            avto.setOnClickListener { showToast("Uzum Avto") }
            sotuvchi.setOnClickListener { showToast("Sotuvchi bo'lish") }
            location.setOnClickListener { showToast("Topshirish punktlari") }
        }


    }


    private var isClicked = true
    private val handler by lazy { Handler(Looper.getMainLooper()) }
    fun showToast(message: String) {
        if (isClicked) {
            isClicked = false
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            handler.postDelayed({
                isClicked = true

            }, 2000)

        }

    }

}