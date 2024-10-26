package uz.gita.examoctoberuzum.presentation.screen.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.preference.Preferences
import uz.gita.examoctoberuzum.databinding.ScreenSplashBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var _binding: ScreenSplashBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ScreenSplashBinding.bind(view)

        Handler().postDelayed({

            if (Preferences.isUserSignedIn()){
                view.post {
                    findNavController().navigate(R.id.action_splashScreen_to_mainScreen)
                }

            }else {

                view.post {
                findNavController().navigate(R.id.action_splashScreen_to_signInScreen )
                    }
            }

        },2000)


    }


}