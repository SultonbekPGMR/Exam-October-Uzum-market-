package uz.gita.examoctoberuzum.presentation.adapter

import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity
import uz.gita.examoctoberuzum.databinding.ItemProductBinding

class ProductAdapter : Adapter<ProductAdapter.Holder>() {

    private lateinit var list: List<ProductEntity>

    lateinit var funItemClicked: (productEntity: ProductEntity) -> Unit


    fun submitList(list: List<ProductEntity>) {
        this.list = list

        notifyDataSetChanged()
    }

    inner class Holder(private val binding: ItemProductBinding) : ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                funItemClicked.invoke(list[adapterPosition])
            }
        }

        fun onBind(productEntity: ProductEntity) {
            binding.name.text = productEntity.name
            binding.image.setImageResource(productEntity.image)
            binding.oldPrice.text = SpannableString("${productEntity.oldPrice} so'm").apply {
                setSpan(
                    StrikethroughSpan(),
                    0,
                    productEntity.oldPrice.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            binding.newPrice.text = productEntity.newPrice + " so'm"
            binding.priceMonthly.text =
                productEntity.newPrice.replace(" ", "").toInt().div(12).toString() + " so'm/oyiga"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        return Holder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.onBind(list[position])
    }

}