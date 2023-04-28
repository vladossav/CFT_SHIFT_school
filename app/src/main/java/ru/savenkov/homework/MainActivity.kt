package ru.savenkov.homework

import android.Manifest.permission.READ_CONTACTS
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(), ContactsAdapter.Listener {
    private val viewModel: MainActivityViewModel by viewModels{
        MainActivityViewModel.ViewModelFactory(AppDatabase.getInstance(applicationContext).contactDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(this, READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(READ_CONTACTS), 200)
        }

        val contactsAdapter = ContactsAdapter(this)
        findViewById<RecyclerView>(R.id.recycler_holder)
            .adapter = contactsAdapter

        findViewById<Button>(R.id.clear_btn).setOnClickListener {
            viewModel.clearAllFromDatabase()
        }

        findViewById<Button>(R.id.add_contact_btn).setOnClickListener {
            showAddContactDialog()
        }

        viewModel.contactList.observe(this) {
            contactsAdapter.reload(it)
        }

    }

    override fun onClick(id: Int, name: String, phone: String) {
        showChangeContactDialog(id, name, phone)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ContextCompat.checkSelfPermission(this, READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            initContacts()
        }
    }

    private fun showChangeContactDialog(id:Int, name: String, phone: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.update_contact_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnUpdate: Button = dialog.findViewById(R.id.update_btn)
        val btnDelete: Button = dialog.findViewById(R.id.delete_btn)
        val btnCancel: Button = dialog.findViewById(R.id.cancel_add_btn)
        val inputName: EditText = dialog.findViewById(R.id.add_name_data)
        val inputPhone: EditText = dialog.findViewById(R.id.add_phone_data)
        inputName.setText(name)
        inputPhone.setText(phone)

        btnUpdate.setOnClickListener {
            dialog.cancel()
            val name = inputName.text.toString()
            val phone = inputPhone.text.toString()
            viewModel.updateContactById(id,name,phone)
        }
        btnDelete.setOnClickListener {
            dialog.cancel()
            viewModel.deleteFromDatabaseById(id)
        }
        btnCancel.setOnClickListener{
            dialog.cancel()
        }
        dialog.show()
    }

    private fun showAddContactDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.add_contact_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnAdd: Button = dialog.findViewById(R.id.add_btn)
        val btnCancel: Button = dialog.findViewById(R.id.cancel_add_btn)
        val inputName: EditText = dialog.findViewById(R.id.add_name_data)
        val inputPhone: EditText = dialog.findViewById(R.id.add_phone_data)
        btnAdd.setOnClickListener {
            dialog.cancel()
            val name = inputName.text.toString()
            val phone = inputPhone.text.toString()
            viewModel.insertToDatabase(Contact(name, phone))
        }
        btnCancel.setOnClickListener{
            dialog.cancel()
        }
        dialog.show()
    }

   @SuppressLint("Range")
   fun initContacts() {
       val cur = contentResolver.query(
           ContactsContract.Contacts.CONTENT_URI,
           null, null, null, null
       )

       if (cur!!.count > 0) {
           while (cur.moveToNext()) {
               val id: String = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID))
               val name: String =
                   cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))


               if (cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                       .toInt() > 0
               ) {
                   val pCur = contentResolver.query(
                       ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                       null,
                       ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                       arrayOf(id),
                       null
                   )
                   while (pCur!!.moveToNext()) {
                       val phoneNo =
                           pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))


                       val contact = Contact(name, phoneNo)

                       viewModel.insertToDatabase(contact)
                   }
                   pCur.close()
               }
           }
       }

       cur.close()
   }

}