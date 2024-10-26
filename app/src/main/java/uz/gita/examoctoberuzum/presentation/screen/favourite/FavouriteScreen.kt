package uz.gita.examoctoberuzum.presentation.screen.favourite

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity
import uz.gita.examoctoberuzum.databinding.ScreenFavouriteBinding
import uz.gita.examoctoberuzum.presentation.adapter.ProductAdapter
import uz.gita.examoctoberuzum.presentation.animation.CubeAnimation
import uz.gita.examoctoberuzum.presentation.animation.MoveAnimation

class FavouriteScreen : Fragment(R.layout.screen_favourite), FavouriteContract.View {



    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
        return if (enter) {
            CubeAnimation.create(CubeAnimation.LEFT, true, 200)
        } else {
            CubeAnimation.create(CubeAnimation.RIGHT, false, 200)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var _binding: ScreenFavouriteBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { ProductAdapter() }
    private lateinit var presenter: FavouriteContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ScreenFavouriteBinding.bind(view)


        addListeners()
        binding.rv.adapter = adapter

        presenter = FavouritePresenter(this)

    }

    private fun addListeners() {
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }


        adapter.btnFavouriteClicked = { pos ->

            presenter.itemFavouriteClicked(pos)
        }

        adapter.funBtnCartClicked = { pos ->
            presenter.itemCartClicked(pos)
        }

        adapter.funItemClicked = {}


    }

    override fun showProducts(list: List<ProductEntity>) {
        adapter.submitList(list)

    }

    override fun setList(list: List<ProductEntity>) {
        adapter.setList(list)
    }


    override fun setList(pos: Int, list: List<ProductEntity>) {
        adapter.setList(list)
        adapter.notifyItemRemoved(pos)
    }

    override fun notifyItemChanged(pos: Int) {
        adapter.notifyItemChanged(pos)
    }

    override fun notifyItemRemoved(pos: Int) {
        adapter.notifyItemRemoved(pos)

    }


    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showOrHideEmptyBox(boolean: Boolean) {
        binding.containerEmpty.visibility = if (boolean) View.VISIBLE else View.GONE

    }

}