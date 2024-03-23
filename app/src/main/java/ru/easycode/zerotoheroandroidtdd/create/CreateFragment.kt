package ru.easycode.zerotoheroandroidtdd.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.CreateLayoutBinding

class CreateFragment : Fragment() {

    private var _binding: CreateLayoutBinding? = null
    private val binding: CreateLayoutBinding
        get() = _binding!!
    private lateinit var viewModel: CreateViewModel
    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CreateLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (requireActivity() as ProvideViewModel).viewModel(CreateViewModel::class.java)

        with(binding) {
            inputEditText.addTextChangedListener {
                if (inputEditText.text.toString().length > 2) {
                    createButton.isEnabled = true
                }
            }
            createButton.setOnClickListener {
                viewModel.add(inputEditText.text.toString())
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(backPressedCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        backPressedCallback.remove()
    }
}