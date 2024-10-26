package uz.gita.examoctoberuzum.presentation.screen.catalog

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.entity.CategoryEntity
import uz.gita.examoctoberuzum.databinding.ScreenCatalogBinding
import uz.gita.examoctoberuzum.presentation.adapter.CategoryAdapter
import uz.gita.examoctoberuzum.presentation.screen.main.MainScreenDirections
import uz.gita.examoctoberuzum.util.navigateTo

class CategoryScreen : Fragment(R.layout.screen_catalog), CategoryContract.View {


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var _binding: ScreenCatalogBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { CategoryAdapter() }

    private lateinit var presenter: CategoryContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ScreenCatalogBinding.bind(view)
        binding.rv.adapter = adapter

        adapter.funCategorySelected = { presenter.btnItemClicked(it) }

        binding.btnAddCategory.setOnClickListener {
            presenter.btnPlusClicked()
        }

        presenter = CategoryPresenter(this)

        setFragmentResultListener("requestKey") { _, bundle ->
            val categoryAdded = bundle.getBoolean("categoryAdded")
            if (categoryAdded) {

                presenter.onResume()
            }
        }


    }

    override fun showCategories(list: List<CategoryEntity>) {

        adapter.submitList(list)
    }

    private var isClicked = true
    override fun openNewCategoryScreen() {
        if (isClicked) {


            findNavController().navigate(R.id.action_catalogScreen_to_newCategory)
            isClicked = false
            Handler().postDelayed({ isClicked = true }, 200)
        }

    }

    override fun openItemsByCategoryScreen(categoryEntity: CategoryEntity) {

        navigateTo(MainScreenDirections.actionMainScreenToProductsByCategoryScreen(categoryEntity))

    }


}