package uz.gita.examoctoberuzum.presentation.screen.home

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
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

    private val productDao by lazy { AppDatabase.instance.getProductDao() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ScreenHomeBinding.bind(view)
        binding.adPager.adapter = AdPagerAdapter()


        binding.rv.adapter = adapter
        adapter.submitList(productDao.getAllProducts())


        addListeners()

        val searchScreen = SearchScreen()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {


                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == null) {
                    return true
                }

                searchScreen.submitQuery(newText)


                return true
            }
        })


        binding.searchView.setOnQueryTextFocusChangeListener { view, b ->
            if (b) {
                childFragmentManager.beginTransaction()
                    .replace(R.id.container_search, searchScreen).addToBackStack("search")
                    .commit()
            } else {
                searchScreen.popBack()
            }
        }


    }


    private var isClicked = true
    private fun addListeners() {
        adapter.showToast = {
            if (isClicked) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                isClicked = false
                Handler().postDelayed({ isClicked = true }, 2000)

            }
        }

        adapter.btnFavouriteClicked = { pos, productEntity ->
            productEntity.isFavourite = if (productEntity.isFavourite == 0) 1 else 0
            productDao.updateProduct(productEntity)
            adapter.setList(productDao.getAllProducts())
            adapter.notifyItemChanged(pos)

        }


        binding.btnFavourite.setOnClickListener {

            navigateTo(R.id.screenFavourite)
        }


    }


}
