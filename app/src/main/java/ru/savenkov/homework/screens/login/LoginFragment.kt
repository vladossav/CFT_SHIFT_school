package ru.savenkov.homework.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.savenkov.homework.utils.Result
import dagger.hilt.android.AndroidEntryPoint
import ru.savenkov.homework.R
import ru.savenkov.homework.databinding.FragmentLoginBinding
import ru.savenkov.homework.utils.snackbar


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        if (savedInstanceState != null) {
            binding.usernameInput.setText(viewModel.username)
            binding.passwordInput.setText(viewModel.password)
        }

        binding.loginButton.setOnClickListener {
            saveStateOfUsernameAndPassword()
            viewModel.login()
        }

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        viewModel.uiState.observe(viewLifecycleOwner) {state ->
            if (state is Result.Error) view!!.snackbar(state.message)
            if (state is Result.Success)
                findNavController().navigate(R.id.action_loginFragment_to_main_graph)
        }

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        viewModel.setInitState()
        saveStateOfUsernameAndPassword()
    }

    private fun saveStateOfUsernameAndPassword() {
        viewModel.username = binding.usernameInput.text.toString()
        viewModel.password = binding.passwordInput.text.toString()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}