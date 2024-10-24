package uz.gita.examoctoberuzum.presentation.screen.newcatalog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.AppDatabase
import uz.gita.examoctoberuzum.data.source.local.entity.CategoryEntity
import uz.gita.examoctoberuzum.databinding.ScreenNewCategoryBinding
import uz.gita.examoctoberuzum.presentation.adapter.NewCategoryAdapter


class NewCategoryDialog : DialogFragment(R.layout.screen_new_category) {

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var _binding: ScreenNewCategoryBinding? = null
    private val binding get() = _binding!!


    private var isImageSelected = false
    private var imageId = R.drawable.cat_bag

    private val categoryDao by lazy { AppDatabase.instance.getCategoryDao() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ScreenNewCategoryBinding.bind(view)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val window = dialog?.window
        val params = window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = params

        val marginInPixels = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            24f,
            resources.displayMetrics
        ).toInt()

        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window?.decorView?.setPadding(marginInPixels, 0, marginInPixels, 0)

        binding.btnClose.setOnClickListener { dialog?.dismiss() }

        val adapter = NewCategoryAdapter()

        adapter.funCategorySelected = { category, isSelected ->
            isImageSelected = isSelected
            if (isImageSelected) {
                imageId = category.image
            }

        }

        binding.rvCategories.adapter = adapter

        adapter.submitList(categoryDao.getAllCategories())


        binding.newBtnAdd.setOnClickListener {
            if (binding.edtName.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Nomni kiriting!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (!isImageSelected) {
                Toast.makeText(context, "Rasm tanglang!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            categoryDao.insertCategory(
                CategoryEntity(0, binding.edtName.text.toString().trim(), imageId)
            )
            Toast.makeText(context, "Qo'shildi", Toast.LENGTH_SHORT).show()
            setFragmentResult("requestKey", bundleOf("categoryAdded" to true))

            dialog?.dismiss()


        }

    }


}