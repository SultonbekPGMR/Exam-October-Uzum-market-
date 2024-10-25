package uz.gita.examoctoberuzum.presentation.screen.itemsbycategory

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity
import uz.gita.examoctoberuzum.databinding.ScreenProductsByCategoryBinding
import uz.gita.examoctoberuzum.presentation.adapter.ProductAdapter

class ProductsByCategoryScreen : Fragment(R.layout.screen_products_by_category),
    ProductsContract.View {
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
            presenter.favouriteClicked(pos, productEntity)

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

    override fun openNewProductScreen() {
        findNavController().navigate(
            ProductsByCategoryScreenDirections.actionProductsByCategoryScreenToNewProductDialog(
                args.categoryData.id
            )
        )
    }

    override fun openDetailsScreen(productEntity: ProductEntity) {

    }

    override fun setList(pos: Int, productsByCategoryId: List<ProductEntity>) {
        adapter.setList(productsByCategoryId)
        adapter.notifyItemChanged(pos)
    }


}