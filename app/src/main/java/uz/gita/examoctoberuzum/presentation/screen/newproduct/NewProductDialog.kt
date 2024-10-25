package uz.gita.examoctoberuzum.presentation.screen.newproduct

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import uz.gita.examoctoberuzum.R
import uz.gita.examoctoberuzum.data.source.local.AppDatabase
import uz.gita.examoctoberuzum.data.source.local.entity.ProductEntity
import uz.gita.examoctoberuzum.databinding.ScreenNewProductBinding
import java.io.File
import java.io.FileOutputStream


class NewProductDialog : DialogFragment(R.layout.screen_new_product) {

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var _binding: ScreenNewProductBinding? = null
    private val binding get() = _binding!!


    private val productDao by lazy { AppDatabase.instance.getProductDao() }
    private lateinit var getContent: ActivityResultLauncher<Intent>

    private var imageUi: Uri? = null

    private val args: NewProductDialogArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ScreenNewProductBinding.bind(view)
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
            resources.displayMetrics.heightPixels * 2 / 3
        )
        window?.decorView?.setPadding(marginInPixels, 0, marginInPixels, 0)

        binding.btnClose.setOnClickListener { dialog?.dismiss() }


        binding.newBtnAdd.setOnClickListener {
            if (binding.edtName.text.toString().trim().isEmpty()
                || binding.edtDesc.text.toString().trim().isEmpty()
                || binding.edtOldPrice.text.toString().trim().isEmpty()
                || binding.edtNewPrice.text.toString().trim().isEmpty()
            ) {
                Toast.makeText(context, "Barchasi to'ldiring!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (imageUi == null) {
                Toast.makeText(context, "Rasm yuklang!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            productDao.insertProduct(
                ProductEntity(
                    0,
                    args.categoryId,
                    binding.edtName.text.toString().trim(),
                    binding.edtDesc.text.toString().trim(),
                    binding.edtOldPrice.text.toString().trim(),
                    binding.edtNewPrice.text.toString().trim(),
                    0,
                    0,
                    0,
                    0,
                    imageUri = imageUi.toString(),
                    binding.edtNewPrice.text.toString().trim(),

                )
            )
            Toast.makeText(context, "Qo'shildi", Toast.LENGTH_SHORT).show()
            setFragmentResult("productKey", bundleOf("productAdded" to true))

            dialog?.dismiss()


        }

        binding.image.setOnClickListener {
            openGallery()
        }


        getContent =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    result.data?.data?.let { uri ->
                        binding.image.setImageURI(uri)
                        binding.txtSelectImage.visibility = View.INVISIBLE
                        imageUi = uri

                    }
                }
            }


    }

    private fun openGallery() {

        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*" // Filter to show only images
        }
        getContent.launch(intent)
    }

}