package ru.savenkov.homework.presentation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ru.savenkov.homework.R
import ru.savenkov.homework.shared.contacts.data.model.Contact
import ru.savenkov.homework.shared.contacts.domain.usecase.AddContactUseCase
import ru.savenkov.homework.shared.contacts.domain.usecase.DeleteContactUseCase
import ru.savenkov.homework.shared.contacts.domain.usecase.EditContactUseCase

class ContactDialogFragment: DialogFragment() {
    private val repository by lazy {
        (requireActivity().application as App).contactRepository
    }
    private val addContactUseCase by lazy { AddContactUseCase(repository) }
    private val editContactUseCase by lazy { EditContactUseCase(repository)  }
    private val deleteContactUseCase by lazy {  DeleteContactUseCase(repository) }
    private val viewModel: ContactDialogViewModel by activityViewModels{
        viewModelFactory {
            initializer {
                ContactDialogViewModel(deleteContactUseCase, editContactUseCase,
                addContactUseCase)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = if (arguments?.getSerializable(TYPE_KEY) == TYPE.ADD)
        inflater.inflate(R.layout.add_contact_dialog, container, false)
    else
        inflater.inflate(R.layout.update_contact_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnCancel: Button = view.findViewById(R.id.cancel_add_btn)
        val inputName: EditText = view.findViewById(R.id.add_name_data)
        val inputPhone: EditText = view.findViewById(R.id.add_phone_data)

        if (arguments?.getSerializable(TYPE_KEY) == TYPE.ADD) {
            val btnAdd: Button = view.findViewById(R.id.add_btn)
            btnAdd.setOnClickListener {
                val name = inputName.text.toString()
                val phone = inputPhone.text.toString()
                viewModel.addContact(Contact(0, name, phone))
                dismiss()
            }
        }
        if (arguments?.getSerializable(TYPE_KEY) == TYPE.EDIT)  {
            val contact = arguments?.getSerializable(CONTACT_KEY) as Contact
            inputName.setText(contact.name)
            inputPhone.setText(contact.phone)

            val btnUpdate: Button = view.findViewById(R.id.update_btn)
            btnUpdate.setOnClickListener {
                val name = inputName.text.toString()
                val phone = inputPhone.text.toString()
                val updatedContact = Contact(contact.id, name, phone)
                viewModel.updateContact(updatedContact)
                dismiss()
            }

            val btnDelete: Button = view.findViewById(R.id.delete_btn)
            btnDelete.setOnClickListener {
                viewModel.deleteContact(contact)
                dismiss()
            }
        }

        btnCancel.setOnClickListener{
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    companion object {
        const val TAG = "ContactDialogFragment"
        const val CONTACT_KEY = "ContactDialogFragmentContactKey"
        const val TYPE_KEY = "ContactDialogFragmentTypeKey"

        enum class TYPE {
            ADD,
            EDIT
        }

        fun addTypeInstance(): ContactDialogFragment {
            val inputArgs = Bundle()
            inputArgs.putSerializable(TYPE_KEY, TYPE.ADD)

            val fragment = ContactDialogFragment()
            fragment.arguments = inputArgs
            return fragment
        }

        fun editTypeInstance(contact: Contact): ContactDialogFragment {
            val inputArgs = Bundle()
            inputArgs.putSerializable(TYPE_KEY, TYPE.EDIT)
            inputArgs.putSerializable(CONTACT_KEY, contact)

            val fragment = ContactDialogFragment()
            fragment.arguments = inputArgs
            return fragment
        }
    }
}