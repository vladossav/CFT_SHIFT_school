package ru.savenkov.homework.screens.loan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.savenkov.homework.R
import ru.savenkov.homework.converter.Converter
import ru.savenkov.homework.data.model.Loan
import ru.savenkov.homework.databinding.FragmentLoanBinding
import ru.savenkov.homework.utils.Result
import ru.savenkov.homework.utils.showSnackbar
import javax.inject.Inject

@AndroidEntryPoint
class LoanFragment : Fragment() {
    @Inject
    lateinit var factory: LoanViewModel.Factory

    private var _binding: FragmentLoanBinding? = null
    private val binding
    get() = _binding!!

    private val viewModel: LoanViewModel by viewModels {
        viewModelFactory {
            initializer {
                factory.create(getLoanId())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoanBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.uiState.observe(viewLifecycleOwner) {state ->
            binding.progressBar.isVisible = state is Result.Loading
            binding.screenInfo.isVisible = state !is Result.Loading
            if (state is Result.Error) view!!.showSnackbar(state.message)
            if (state is Result.Success) setLoanDetails(state.data)
        }

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun getLoanId(): Long =
        if (arguments?.containsKey(LOAN_ID_KEY) == true) {
            val loanId = requireArguments().getLong(LOAN_ID_KEY)
            loanId
        } else -1

    private fun setLoanDetails(loan: Loan) {
        val date = Converter.toLocalDateTime(loan.date)
        binding.loanDetailsTitle.text = resources.getString(R.string.loan_details_title, date)
        binding.firstname.text = loan.firstName
        binding.lastname.text = loan.lastName
        binding.phone.text = loan.phoneNumber
        binding.period.text = loan.period.toString()
        binding.percent.text = loan.percent.toString()
        binding.amount.text = loan.amount.toString()
        binding.status.text = Converter.toLocalLanguage(resources, loan.state)
    }

    companion object {
        const val LOAN_ID_KEY = "LOAN_ID_KEY"
    }

}