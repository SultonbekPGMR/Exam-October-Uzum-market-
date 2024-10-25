package uz.gita.examoctoberuzum.util

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import uz.gita.examoctoberuzum.R

fun Fragment.navigateTo(destinationId: Int) {
    val navHostFragment = requireActivity().supportFragmentManager
        .findFragmentById(R.id.nav_host) as NavHostFragment
    val navController = navHostFragment.navController
    navController.navigate(destinationId)
}
fun Fragment.navigateTo(directions: NavDirections) {
    val navHostFragment = requireActivity().supportFragmentManager
        .findFragmentById(R.id.nav_host) as NavHostFragment
    val navController = navHostFragment.navController
    navController.navigate(directions)
}
