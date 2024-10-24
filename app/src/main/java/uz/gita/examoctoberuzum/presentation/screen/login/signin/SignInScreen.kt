package uz.gita.examoctoberuzum.presentation.screen.login.signin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.databinding.ScreenSignInBinding

class SignInScreen : Fragment(R.layout.screen_sign_in), SignInContract.View {

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var _binding: ScreenSignInBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: SignInContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ScreenSignInBinding.bind(view)
        presenter = SignInPresenter(this)

        binding.btnSignIn.setOnClickListener {

            presenter.btnSignInClicked(
                binding.signInEdtPhoneNumber.text.toString().trim(),
                binding.signInEdtPassword.text.toString().trim()
            )

        }


        binding.btnRegister.setOnClickListener {

            presenter.btnRegisterClicked()

        }

    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun openRegisterScreen() {
        findNavController().navigate(R.id.action_signInScreen_to_signUpScreen)
    }

    override fun openMainScreen() {
        findNavController().navigate(R.id.action_signInScreen_to_mainScreen)
    }


}