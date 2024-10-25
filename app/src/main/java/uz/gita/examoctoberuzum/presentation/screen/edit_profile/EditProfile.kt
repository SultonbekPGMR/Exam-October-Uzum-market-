package uz.gita.examoctoberuzum.presentation.screen.edit_profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.AppDatabase
import uz.gita.examoctoberuzum.data.source.local.entity.UserEntity
import uz.gita.examoctoberuzum.data.source.preference.Preferences
import uz.gita.examoctoberuzum.databinding.ScreenEditProfileBinding

class EditProfile : Fragment(R.layout.screen_edit_profile) {

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var _binding: ScreenEditProfileBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ScreenEditProfileBinding.bind(view)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        val userDao = AppDatabase.instance.getUserDao()

        val user: UserEntity =
            userDao.getUserByPhoneNumber(Preferences.getCurrentUserPhoneNumber())!!


        binding.apply {
            edtFullName.setText(user.fullName)
            edtAge.setText(user.age)
            edtPhoneNumber.setText(user.phoneNumber)
        }

        binding.btnSave.setOnClickListener {

            val newData =
                UserEntity(
                    id = user.id,
                    fullName = binding.edtFullName.text.toString().trim(),
                    age = binding.edtAge.text.toString().trim(),
                    phoneNumber = binding.edtPhoneNumber.text.toString().trim(),
                    password = user.password
                )

            if (newData.fullName.isEmpty() || newData.age.isEmpty() || newData.phoneNumber.isEmpty()) {
                Toast.makeText(context, "barchasini To'ldiring!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            userDao.updateUser(newData)
            Preferences.saveCurrentUserPhoneNumber(newData.phoneNumber)
            Toast.makeText(context, "Saqlandi", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()

        }


    }


}