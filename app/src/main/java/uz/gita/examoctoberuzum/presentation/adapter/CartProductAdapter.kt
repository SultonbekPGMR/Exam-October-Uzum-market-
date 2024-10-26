package uz.gita.examoctoberuzum.presentation.adapter

import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity
import uz.gita.examoctoberuzum.databinding.ItemCartProductBinding
import uz.gita.examoctoberuzum.util.formatPrice

class CartProductAdapter : Adapter<CartProductAdapter.Holder>() {

    private var list: ArrayList<ProductEntity> = ArrayList()

    lateinit var funItemClicked: (product: ProductEntity) -> Unit
    lateinit var funDecrementClicked: (position: Int, product: ProductEntity) -> Unit
    lateinit var funIncrementClicked: (position: Int, product: ProductEntity) -> Unit
    lateinit var funFavouriteClicked: (position: Int) -> Unit
    lateinit var funDeleteClicked: (position: Int) -> Unit


    fun setList(newList: List<ProductEntity>) {
        list.clear()
        list.addAll(newList)

    }


    fun submitList(list: List<ProductEntity>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()

    }


    inner class Holder(private val binding: ItemCartProductBinding) : ViewHolder(binding.root) {

        init {

            binding.btnDelete.setOnClickListener { funDeleteClicked.invoke(adapterPosition) }

            binding.btnFavourite.setOnClickListener { funFavouriteClicked.invoke(adapterPosition) }

            binding.root.setOnClickListener {
                funItemClicked.invoke(list[adapterPosition])
            }

            binding.btnDecrement.setOnClickListener {
                funDecrementClicked.invoke(
                    adapterPosition,
                    list[adapterPosition]
                )
            }
            binding.btnIncrement.setOnClickListener {
                funIncrementClicked.invoke(
                    adapterPosition,
                    list[adapterPosition]
                )
            }

        }


        fun onBind(productEntity: ProductEntity) {

            binding.btnFavourite.setImageResource(if (productEntity.isFavourite == 0) R.drawable.baseline_favorite_border_24 else R.drawable.baseline_favorite_24)

            binding.count.text = productEntity.countInCart.toString()

            if (productEntity.isDefault == 1) binding.image.setImageResource(productEntity.image)
            else Glide.with(binding.image.context)
                .load(productEntity.imageUri) // Load the image from the content URI
                .into(binding.image) // Set the image into your ImageView

            binding.name.text = productEntity.name
            binding.totalPrice.text =
                (list[adapterPosition].newPrice.toInt() * list[adapterPosition].countInCart).toString()
                    .formatPrice()
            binding.oldPrice.text = SpannableString(productEntity.oldPrice.formatPrice()).apply {
                setSpan(
                    StrikethroughSpan(),
                    0,
                    productEntity.oldPrice.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            binding.newPrice.text = productEntity.newPrice.formatPrice()


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemCartProductBinding.inflate(
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