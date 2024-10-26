package uz.gita.examoctoberuzum.presentation.screen.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity
import uz.gita.examoctoberuzum.databinding.ScreenHomeBinding
import uz.gita.examoctoberuzum.presentation.adapter.AdPagerAdapter
import uz.gita.examoctoberuzum.presentation.adapter.ProductAdapter
import uz.gita.examoctoberuzum.util.navigateTo

class HomeScreen : Fragment(R.layout.screen_home), HomeContract.View {
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var _binding: ScreenHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { ProductAdapter() }

    private lateinit var presenter: HomePresenter

    private lateinit var searchScreen: SearchScreen

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ScreenHomeBinding.bind(view)
        binding.adPager.adapter = AdPagerAdapter()

        searchScreen = SearchScreen()

        binding.rv.adapter = adapter

        addListeners()

        presenter = HomePresenter(this)


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {


                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == null) {
                    return true
                }
                presenter.searchQueryTextChanged(newText)


                return true
            }
        })

        binding.searchView.setOnQueryTextFocusChangeListener { _, b ->

            presenter.searchQueryFocusChanged(b)

        }

        binding.btnBack.setOnClickListener {
            searchScreen.popBack()
            binding.btnBack.visibility = View.GONE
            binding.searchView.clearFocus()
        }


    }


    private fun addListeners() {
        adapter.btnFavouriteClicked = { pos ->
            presenter.itemFavouriteClicked(pos)
        }

        adapter.funBtnCartClicked = { pos ->
            presenter.itemCartClicked(pos)
        }


        binding.btnFavourite.setOnClickListener {
            presenter.btnFavouriteClicked()
        }


    }

    override fun showProducts(list: List<ProductEntity>) {
        adapter.submitList(list)
    }

    override fun setList(list: List<ProductEntity>) {
        adapter.setList(list)
    }

    override fun notifyItemChanged(pos: Int) {
        adapter.notifyItemChanged(pos)
    }

    override fun openSearchScreen() {
        childFragmentManager.beginTransaction()
            .replace(R.id.container_search, searchScreen).addToBackStack("search")
            .commit()
        binding.btnBack.visibility = View.VISIBLE

    }

    override fun closeSearchScreen() {
        searchScreen.popBack()
        binding.btnBack.visibility = View.GONE
    }

    override fun openFavouriteScreen() {
        binding.searchView.clearFocus()
        navigateTo(R.id.screenFavourite)
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    override fun sendQueryToSearchScreen(query: String) {
        searchScreen.submitQuery(query)
    }


}
