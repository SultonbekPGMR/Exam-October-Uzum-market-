package uz.gita.examoctoberuzum.presentation.screen.cart

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity
import uz.gita.examoctoberuzum.databinding.ScreenCartBinding
import uz.gita.examoctoberuzum.presentation.adapter.CartProductAdapter

class CartScreen : Fragment(R.layout.screen_cart), CartContract.View {

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var _binding: ScreenCartBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { CartProductAdapter() }

    private lateinit var presenter: CartContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ScreenCartBinding.bind(view)

        binding.rv.adapter = adapter

        adapter.funItemClicked = { presenter.itemClicked(it) }
        adapter.funDecrementClicked =
            { position, product -> presenter.decrementClicked(position, product) }
        adapter.funIncrementClicked =
            { position, product -> presenter.incrementClicked(position, product) }

        binding.btnConfirm.setOnClickListener { presenter.btnConfirmClicked() }

        presenter = CartPresenter(this)

    }

    override fun showProducts(list: List<ProductEntity>) {
        adapter.submitList(list)
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun setTextToTitle(text: String) {
        binding.title.text = text
    }

    override fun setTextToToTalPrice(text: String) {
        binding.totalCost.text = text + " so'm"
    }

    override fun setTextToProductCount(text: String) {
        binding.totalProductCount.text = text + " ta mahsulot"
    }

    override fun enableConfirmBtn() {
        binding.btnConfirm.isEnabled = true
    }

    override fun disableConfirmBtn() {
        binding.btnConfirm.isEnabled = false

    }

    override fun setList(list: List<ProductEntity>) {
        adapter.setList(list)
    }

    override fun notifyItemChanged(pos: Int) {
        adapter.notifyItemChanged(pos)
    }

    override fun notifyItemDeleted(pos: Int) {
        adapter.notifyItemRemoved(pos)
    }

    override fun openDetailsScreen() {

    }


}