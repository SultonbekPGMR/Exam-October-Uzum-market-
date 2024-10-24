package uz.gita.examoctoberuzum.presentation.screen.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.AppDatabase
import uz.gita.examoctoberuzum.databinding.ScreenHomeBinding
import uz.gita.examoctoberuzum.presentation.adapter.AdPagerAdapter
import uz.gita.examoctoberuzum.presentation.adapter.ProductAdapter
import uz.gita.examoctoberuzum.util.navigateTo

class HomeScreen : Fragment(R.layout.screen_home) {
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var _binding: ScreenHomeBinding? = null
    private val binding get() = _binding!!


    private val adapter by lazy { ProductAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ScreenHomeBinding.bind(view)
        binding.adPager.adapter = AdPagerAdapter()


        binding.rv.adapter  = adapter
        adapter.submitList(AppDatabase.instance.getProductDao().getAllProducts())



        binding.btnFavourite.setOnClickListener {

            navigateTo(R.id.screenFavourite)
        }



    }


}
