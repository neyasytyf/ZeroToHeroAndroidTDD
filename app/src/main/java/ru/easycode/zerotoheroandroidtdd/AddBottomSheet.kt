package ru.easycode.zerotoheroandroidtdd

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentAddBinding

class AddBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding: FragmentAddBinding
        get() = _binding!!
    private lateinit var viewModel: AddViewModel
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        viewModel = (requireActivity() as ProvideViewModel).viewModel(AddViewModel::class.java)
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.comeback()
                dismiss()
            }
        }
        (dialog as BottomSheetDialog).onBackPressedDispatcher.addCallback(onBackPressedCallback)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            viewModel.add(binding.addInputEditText.text.toString())
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        onBackPressedCallback.remove()
    }

    companion object {
        const val TAG = "AddBottomSheet"
    }
}