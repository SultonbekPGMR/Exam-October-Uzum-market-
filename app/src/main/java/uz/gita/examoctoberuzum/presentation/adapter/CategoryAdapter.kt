package uz.gita.examoctoberuzum.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.examoctoberuzum.data.source.local.entity.CategoryEntity
import uz.gita.examoctoberuzum.databinding.ItemCategoryBinding

class CategoryAdapter : Adapter<CategoryAdapter.Holder>() {

    private  var list: ArrayList<CategoryEntity> = ArrayList()

    lateinit var funCategorySelected: (category: CategoryEntity) -> Unit


    fun submitList(list: List<CategoryEntity>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()

    }


    inner class Holder(private val binding: ItemCategoryBinding) : ViewHolder(binding.root) {

        init {

            binding.root.setOnClickListener {
                funCategorySelected.invoke(list[adapterPosition])
            }

        }


        fun onBind(categoryEntity: CategoryEntity) {
            binding.title.text = categoryEntity.name
            binding.image.setImageResource(categoryEntity.image)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemCategoryBinding.inflate(
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