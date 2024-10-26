package uz.gita.examoctoberuzum.presentation.screen.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.widget.Toast
import androidx.fragment.app.Fragment
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.AppDatabase
import uz.gita.examoctoberuzum.databinding.ScreenSearchBinding
import uz.gita.examoctoberuzum.presentation.adapter.SearchAdapter
import uz.gita.examoctoberuzum.presentation.animation.CubeAnimation
import uz.gita.examoctoberuzum.presentation.animation.FlipAnimation
import uz.gita.examoctoberuzum.presentation.animation.MoveAnimation
import uz.gita.examoctoberuzum.presentation.animation.PushPullAnimation


class SearchScreen : Fragment(R.layout.screen_search) {


    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
        return if (enter) {
            MoveAnimation.create(MoveAnimation.UP, true, 200)
        } else {
            MoveAnimation.create(MoveAnimation.DOWN, false, 200)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var _binding: ScreenSearchBinding? = null
    private val binding get() = _binding!!


    private var isPopped = false

    private val adapter by lazy { SearchAdapter() }
    private var message = ""
    private val productDao by lazy { AppDatabase.instance.getProductDao() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ScreenSearchBinding.bind(view)
        isPopped = false

        binding.rv.adapter = adapter
        adapter.submitList(productDao.getAllProducts())

        adapter.btnFavouriteClicked = { pos, productEntity ->
            productEntity.isFavourite = if (productEntity.isFavourite == 0) 1 else 0
            Log.d("TTTYYYREREER", "onViewCreated: ")
            productDao.updateProduct(productEntity)
            if (message.isEmpty()) adapter.submitList(productDao.getAllProducts())
            else adapter.submitList(productDao.getProductsByQuery(message))
            adapter.notifyItemChanged(pos)


        }
        adapter.showToast = {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        }






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
        }
        this.message = message

    }


}