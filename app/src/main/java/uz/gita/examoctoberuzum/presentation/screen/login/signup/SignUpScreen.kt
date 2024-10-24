package uz.gita.examoctoberuzum.presentation.screen.login.signup

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.databinding.ScreenSignUpBinding

class SignUpScreen : Fragment(R.layout.screen_sign_up), SignupContract.View {

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var _binding: ScreenSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: SignupContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ScreenSignUpBinding.bind(view)

        presenter = SignUpPresenter(this)

        binding.btnLogIn.setOnClickListener { presenter.btnSignInClicked() }


        binding.btnRegister.setOnClickListener {
            presenter.btnSignUpClicked(
                name = binding.registerEdtFullName.text.toString().trim(),
                age = binding.registerEdtAge.text.toString().trim(),
                phoneNumber = binding.registerEdtPhoneNumber.text.toString().trim(),
                password1 = binding.registerEdtPassword1.text.toString().trim(),
                password2 = binding.registerEdtPassword2.text.toString().trim(),
            )
        }



    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun openLoginScreen() {
        findNavController().popBackStack()
    }

    override fun openLogInScreenAfterRegister() {
        findNavController().navigate(R.id.action_signUpScreen_to_signInScreen)
    }


}