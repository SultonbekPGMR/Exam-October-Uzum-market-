package uz.gita.examoctoberuzum.presentation.screen.itemsbycategory

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity
import uz.gita.examoctoberuzum.databinding.ScreenProductsByCategoryBinding
import uz.gita.examoctoberuzum.presentation.adapter.ProductAdapter
import uz.gita.examoctoberuzum.presentation.animation.MoveAnimation

class ProductsByCategoryScreen : Fragment(R.layout.screen_products_by_category),

    ProductsContract.View {
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
        return if (enter) {
            MoveAnimation.create(MoveAnimation.LEFT, true, 200)
        } else {
            MoveAnimation.create(MoveAnimation.RIGHT, false, 200)

        }
    }

    private var _binding: ScreenProductsByCategoryBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { ProductAdapter() }

    private lateinit var presenter: ProductsContract.Presenter

    private val args: ProductsByCategoryScreenArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ScreenProductsByCategoryBinding.bind(view)

        binding.title.text = args.categoryData.name
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }


        binding.rv.adapter = adapter
        presenter = ProductsPresenter(this, args.categoryData.id)

        addListeners()

    }


    private fun addListeners() {
        adapter.btnFavouriteClicked = { pos ->
            presenter.itemFavouriteClicked(pos)

        }

        adapter.funBtnCartClicked = {pos ->
            presenter.itemCartClicked(pos)

        }

        setFragmentResultListener("productKey") { _, bundle ->
            val categoryAdded = bundle.getBoolean("productAdded")
            if (categoryAdded) {

                presenter.onResume()
            }
        }


        binding.btnAddProduct.setOnClickListener { presenter.btnAddProductClicked() }


        adapter.funItemClicked = {
            presenter.btnItemClicked(it)
        }
    }


    override fun showProducts(list: List<ProductEntity>) {
        adapter.submitList(list)
    }
    private var isClicked = true
    override fun openNewProductScreen() {
        if (isClicked){
            isClicked =false
            findNavController().navigate(
                ProductsByCategoryScreenDirections.actionProductsByCategoryScreenToNewProductDialog(
                    args.categoryData.id
                )
            )
            Handler(Looper.getMainLooper()).postDelayed({
                isClicked = true
            },200)
        }

    }

    override fun openDetailsScreen(productEntity: ProductEntity) {

    }

    override fun setList( productsByCategoryId: List<ProductEntity>) {
        adapter.setList(productsByCategoryId)
    }

    override fun notifyItemChanged(pos: Int) {
        adapter.notifyItemChanged(pos)
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}