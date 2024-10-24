package uz.gita.examoctoberuzum.presentation.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.databinding.ItemAdBinding


class AdPagerAdapter : Adapter<AdPagerAdapter.ItemHolder>() {




    inner class ItemHolder(private val binding: ItemAdBinding) : ViewHolder(binding.root) {


        fun onBind(position: Int) {
           when(position){
               0 -> binding.image.setImageResource(R.drawable.ad2)
               1 -> binding.image.setImageResource(R.drawable.ad1)
               2 -> binding.image.setImageResource(R.drawable.ad3)
               3 -> binding.image.setImageResource(R.drawable.ad2)
           }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemAdBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = 4

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.onBind(position)
    }

}