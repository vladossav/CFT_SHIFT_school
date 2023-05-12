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
import ru.savenkov.homework.R
import ru.savenkov.homework.data.Contact

class ContactDialogFragment: DialogFragment() {

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
                //viewModel.insertContact(Contact(0,name, phone))
            }
        }
        if (arguments?.getSerializable(TYPE_KEY) == TYPE.EDIT)  {
            val contact = arguments?.getSerializable(CONTACT_KEY) as Contact
            inputName.setText(contact.name)
            inputPhone.setText(contact.phone)
            val btnUpdate: Button = view.findViewById(R.id.update_btn)
            val btnDelete: Button = view.findViewById(R.id.delete_btn)
            btnUpdate.setOnClickListener {
                val name = inputName.text.toString()
                val phone = inputPhone.text.toString()
                val contact = Contact(contact.id, name, phone)
                //viewModel.updateContact(contact)
            }
            btnDelete.setOnClickListener {
                dismiss()
                //viewModel.deleteContact(contact)
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