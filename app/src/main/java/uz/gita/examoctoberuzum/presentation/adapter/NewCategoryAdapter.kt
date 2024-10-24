package uz.gita.examoctoberuzum.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.entity.CategoryEntity
import uz.gita.examoctoberuzum.databinding.ItemNewCategoryBinding

class NewCategoryAdapter : Adapter<NewCategoryAdapter.Holder>() {

    private lateinit var list: List<CategoryEntity>

    lateinit var funCategorySelected: (category: CategoryEntity, isSelected: Boolean) -> Unit


    private lateinit var checkedUncheckedList: MutableList<Boolean>
    private var lastIndex = -1


    fun submitList(list: List<CategoryEntity>) {
        this.list = list
        notifyDataSetChanged()

        // Create a list of the same size as `list`, filled with `false` values.
        this.checkedUncheckedList = MutableList(list.size) { false }
    }


    inner class Holder(private val binding: ItemNewCategoryBinding) : ViewHolder(binding.root) {

        init {

            binding.root.setOnClickListener {

                if (lastIndex == adapterPosition) {
                    checkedUncheckedList[adapterPosition] = !checkedUncheckedList[adapterPosition]
                    notifyItemChanged(adapterPosition)
                    funCategorySelected.invoke(
                        list[adapterPosition],
                        checkedUncheckedList[adapterPosition]
                    )
                    return@setOnClickListener
                }


                if (lastIndex != -1) {
                    checkedUncheckedList[lastIndex] = false
                    notifyItemChanged(lastIndex)
                }
                checkedUncheckedList[adapterPosition] = !checkedUncheckedList[adapterPosition]
                notifyItemChanged(adapterPosition)
                lastIndex = adapterPosition
                funCategorySelected.invoke(
                    list[adapterPosition],
                    checkedUncheckedList[adapterPosition]
                )


            }


        }

        fun onBind(categoryEntity: CategoryEntity) {
            binding.catImage.setImageResource(categoryEntity.image)


            if (checkedUncheckedList[adapterPosition]) {
                binding.backContainer.setBackgroundResource(R.drawable.category_back_selected)
            } else {
                binding.backContainer.setBackgroundResource(R.drawable.category_back)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemNewCategoryBinding.inflate(
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