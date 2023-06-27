package ru.savenkov.homework.screens.login.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.savenkov.homework.R
import ru.savenkov.homework.databinding.FragmentLoginBinding
import ru.savenkov.homework.databinding.FragmentRegistrationBinding
import ru.savenkov.homework.utils.Result
import ru.savenkov.homework.utils.snackbar

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private val viewModel: RegistrationViewModel by viewModels()
    private var _binding: FragmentRegistrationBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        if (savedInstanceState != null) {
            binding.usernameInput.setText(viewModel.username)
            binding.passwordInput.setText(viewModel.password1)
            binding.repeatPasswordInput.setText(viewModel.password2)
        }

        binding.registerButton.setOnClickListener {
            saveStateOfUsernameAndPassword()
            viewModel.register()
        }

        viewModel.uiState.observe(viewLifecycleOwner) {state ->
            if (state is Result.Error) view!!.snackbar(state.message)
            if (state is Result.Success) findNavController().popBackStack()
        }

        return binding.root
    }

    private fun saveStateOfUsernameAndPassword() {
        viewModel.username = binding.usernameInput.text.toString()
        viewModel.password1 = binding.passwordInput.text.toString()
        viewModel.password2 = binding.repeatPasswordInput.text.toString()
    }

    override fun onPause() {
        super.onPause()
        viewModel.setInitState()
        saveStateOfUsernameAndPassword()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}