package uz.gita.examoctoberuzum.presentation.screen.profile

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.AppDatabase
import uz.gita.examoctoberuzum.data.source.local.entity.UserEntity
import uz.gita.examoctoberuzum.data.source.preference.Preferences
import uz.gita.examoctoberuzum.databinding.ScreenProfileBinding
import uz.gita.examoctoberuzum.presentation.screen.main.MainScreenDirections
import uz.gita.examoctoberuzum.util.navigateTo
import java.util.Objects

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


        val user: UserEntity = AppDatabase.instance.getUserDao()
            .getUserByPhoneNumber(Preferences.getCurrentUserPhoneNumber())!!

        binding.name.text = user.fullName
        binding.phoneNumber.text = user.phoneNumber


        binding.btnEdit.setOnClickListener {

            navigateTo(MainScreenDirections.actionMainScreenToEditProfile())


        }


        binding.btnLogOut.setOnClickListener {

            logOutClicked()

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

    private fun logOutClicked() {

        val dialogLayout =
            LayoutInflater.from(context).inflate(R.layout.confirm_exit_dialog, binding.root, false)

        val btnClose = dialogLayout.findViewById<Button>(R.id.btn_no)
        val btnFinish = dialogLayout.findViewById<Button>(R.id.btn_yes)

        val dialog = AlertDialog.Builder(requireContext()).setView(dialogLayout).create()
        Objects.requireNonNull(dialog.window)
            ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnClose.setOnClickListener { view: View? -> dialog.dismiss() }

        btnFinish.setOnClickListener {
            dialog.dismiss()
            Preferences.isUserSignedIn(false)
            navigateTo(MainScreenDirections.actionMainScreenToSignInScreen())
        }


        dialog.show()

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