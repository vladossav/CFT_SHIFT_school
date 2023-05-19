package ru.savenkov.homework.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import ru.savenkov.homework.feature.contacts.R
import ru.savenkov.homework.shared.contacts.domain.usecase.AddContactUseCase
import ru.savenkov.homework.shared.contacts.domain.usecase.DeleteAllContactsUseCase
import ru.savenkov.homework.shared.contacts.domain.usecase.GetAllContactsUseCase


class MainFragment : Fragment() {
    private val deleteAllContactsUseCase by lazy {
        DeleteAllContactsUseCase((requireContext().applicationContext as App).contactRepository)
    }
    private val getAllContactsUseCase by lazy {
        GetAllContactsUseCase((requireContext().applicationContext as App).contactRepository)
    }
    private val addContactUseCase by lazy {
        AddContactUseCase((requireContext().applicationContext as App).contactRepository)
    }

    private val viewModel: MainFragmentViewModel by activityViewModels {
        viewModelFactory {
            initializer {
                MainFragmentViewModel(deleteAllContactsUseCase, getAllContactsUseCase, addContactUseCase)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contactsAdapter = ContactsAdapter {contact ->
            ContactDialogFragment.editTypeInstance(contact)
                .show(parentFragmentManager, ContactDialogFragment.TAG)
        }
        view.findViewById<RecyclerView>(R.id.recycler_holder)
            .adapter = contactsAdapter

        view.findViewById<Button>(R.id.clear_btn).setOnClickListener {
            viewModel.clearAll()
        }

        view.findViewById<Button>(R.id.add_contact_btn).setOnClickListener {
            ContactDialogFragment.addTypeInstance()
                .show(parentFragmentManager, ContactDialogFragment.TAG)
        }

        viewModel.contactList.observe(viewLifecycleOwner) {
            contactsAdapter.reload(it)
        }
    }



}