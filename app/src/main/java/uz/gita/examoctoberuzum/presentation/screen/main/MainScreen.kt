package uz.gita.examoctoberuzum.presentation.screen.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.databinding.ScreenMainBinding

class MainScreen : Fragment(R.layout.screen_main) {
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var _binding: ScreenMainBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ScreenMainBinding.bind(view)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.bottom_nav_host) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottomNav, navController)


    }


}