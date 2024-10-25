package uz.gita.examoctoberuzum.presentation.screen.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.AppDatabase
import uz.gita.examoctoberuzum.databinding.ScreenSearchBinding
import uz.gita.examoctoberuzum.presentation.adapter.SearchAdapter

class SearchScreen : Fragment(R.layout.screen_search) {


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var _binding: ScreenSearchBinding? = null
    private val binding get() = _binding!!


    private var isPopped = false

    private lateinit var adapter: SearchAdapter
    private var message = ""
    private val productDao by lazy { AppDatabase.instance.getProductDao() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ScreenSearchBinding.bind(view)
        isPopped = false

        adapter = SearchAdapter()

        binding.rv.adapter = adapter

        adapter.btnFavouriteClicked = { pos, productEntity ->
            productEntity.isFavourite = if (productEntity.isFavourite == 0) 1 else 0
            productDao.updateProduct(productEntity)
            if (message.isEmpty()) adapter.submitList(productDao.getAllProducts())
            else adapter.submitList(productDao.getProductsByQuery(message))

        }
        adapter.showToast = {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        }



        adapter.submitList(productDao.getAllProducts())


    }

    fun popBack() {
        if (!isPopped) {
            parentFragmentManager.popBackStack()
            isPopped = true
        }
    }


    fun submitQuery(message: String) {
        if (message.isEmpty()) {
            adapter.submitList(productDao.getAllProducts())
        } else {
            adapter.submitList(productDao.getProductsByQuery(message))
            Log.d("TTTTWWWWEEE", "submitQuery: $message")
        }
        this.message = message

    }

}