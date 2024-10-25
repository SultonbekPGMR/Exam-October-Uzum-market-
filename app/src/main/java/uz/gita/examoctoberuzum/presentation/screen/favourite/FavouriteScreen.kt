package uz.gita.examoctoberuzum.presentation.screen.favourite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity
import uz.gita.examoctoberuzum.databinding.ScreenFavouriteBinding
import uz.gita.examoctoberuzum.databinding.ScreenHomeBinding
import uz.gita.examoctoberuzum.presentation.adapter.ProductAdapter

class FavouriteScreen: Fragment(R.layout.screen_favourite),FavouriteContract.View {
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var _binding: ScreenFavouriteBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { ProductAdapter() }
    private lateinit var presenter:FavouriteContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ScreenFavouriteBinding.bind(view)


        addListeners()
        binding.rv.adapter = adapter

        presenter = FavouritePresenter(this)

    }

    private fun addListeners() {
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }


        adapter.btnFavouriteClicked = {pos, productEntity ->

            presenter.btnFavouriteClicked(pos,productEntity)
        }

        adapter.funItemClicked = {}


        adapter.showToast = {

        }

    }

    override fun showProducts(list: List<ProductEntity>) {
        adapter.submitList(list)
    }

    override fun setList(pos: Int, list: List<ProductEntity>) {
        adapter.setList(list)
        adapter.notifyItemRemoved(pos)
    }

}