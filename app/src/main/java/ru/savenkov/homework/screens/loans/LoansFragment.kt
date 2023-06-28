package ru.savenkov.homework.screens.loans

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import dagger.hilt.android.AndroidEntryPoint
import ru.savenkov.homework.R
import ru.savenkov.homework.databinding.FragmentLoansBinding
import ru.savenkov.homework.screens.loan.LoanFragment
import ru.savenkov.homework.utils.Result
import ru.savenkov.homework.utils.showSnackbar


@AndroidEntryPoint
class LoansFragment : Fragment() {

    private val viewModel: LoansViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null && !viewModel.checkWasFirstLogin())
            findNavController().navigate(R.id.action_loansFragment_to_instructionFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoansBinding.inflate(inflater, container, false)

        val adapter = LoanAdapter {loanId ->
            findNavController().navigate(
                R.id.action_loansFragment_to_loanFragment,
                bundleOf(LoanFragment.LOAN_ID_KEY to loanId)
            )
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getLoans()
            binding.swipeRefresh.isRefreshing = false
        }

        viewModel.loansState.observe(viewLifecycleOwner) {state ->
            binding.progressBar.isVisible = state is Result.Loading
            if (state is Result.Success) {
                binding.loansEmptyListLabel.isVisible = state.data.isEmpty()
                adapter.loanList = state.data
            }
            if (state is Result.Error) {
                view!!.showSnackbar(state.message)
            }
        }
        
        binding.loansRecycler.adapter = adapter
        binding.infoButton.setOnClickListener {
            findNavController().navigate(R.id.action_loansFragment_to_instructionFragment)
        }

        binding.createLoanButton.setOnClickListener {
            findNavController().navigate(R.id.action_loansFragment_to_createLoanFragment)
        }

        binding.logoutButton.setOnClickListener {
            logoutAndNavigateToSignScreen()
        }

        return binding.root
    }

    private fun logoutAndNavigateToSignScreen() {
        viewModel.logout()
        val navHost = requireActivity().supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navHost.navController.navigate(R.id.loginFragment, null, navOptions {
            popUpTo(R.id.loansFragment) {
                inclusive = true
            }
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.getLoans()
    }

}