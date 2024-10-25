package uz.gita.examoctoberuzum.presentation.adapter

import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.AppDatabase
import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity
import uz.gita.examoctoberuzum.databinding.ItemProductBinding

class SearchAdapter : ListAdapter<ProductEntity, SearchAdapter.Holder>(DictionaryDiffUtil) {
    lateinit var btnFavouriteClicked: (pos: Int, productEntity: ProductEntity) -> Unit

    lateinit var showToast: (message: String) -> Unit

    object DictionaryDiffUtil : DiffUtil.ItemCallback<ProductEntity>() {
        override fun areItemsTheSame(
            oldItem: ProductEntity,
            newItem: ProductEntity,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProductEntity,
            newItem: ProductEntity,
        ): Boolean {
            return oldItem == newItem
        }
    }


  inner  class Holder(private val binding: ItemProductBinding) : ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
//                funItemClicked.invoke(list[adapterPosition])
            }

            binding.btnAddToCart.setOnClickListener {
                if (getItem(adapterPosition).countInCart == 0) {
                    getItem(adapterPosition).countInCart++
                    AppDatabase.instance.getProductDao().updateProduct(getItem(adapterPosition))
                    showToast.invoke("Savatga qo'shildi")

                } else {
                    showToast.invoke("Allaqachon savatda")
                }
            }

        }

        init {
            binding.btnFavourite.setOnClickListener {
              btnFavouriteClicked.invoke(adapterPosition, getItem(adapterPosition))
            }
        }


        fun onBind(productEntity:ProductEntity) {
            if (productEntity.isDefault == 1) binding.image.setImageResource(productEntity.image)
            else Glide.with(binding.image.context)
                .load(productEntity.imageUri) // Load the image from the content URI
                .into(binding.image) // Set the image into your ImageView


            binding.btnFavourite.setImageResource(if (productEntity.isFavourite == 0) R.drawable.baseline_favorite_border_24 else R.drawable.baseline_favorite_24)


            binding.name.text = productEntity.name
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.onBind(getItem(position))
    }

}