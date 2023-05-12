package ru.savenkov.homework.presentation

import android.Manifest.permission.READ_CONTACTS
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import ru.savenkov.homework.R
import ru.savenkov.homework.data.model.Contact


class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels{
        viewModelFactory {
            initializer {
                MainActivityViewModel((application as App))
               }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(this, READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(READ_CONTACTS), 200)
        }

        val contactsAdapter = ContactsAdapter {contact ->
            ContactDialogFragment.editTypeInstance(contact)
                .show(supportFragmentManager, ContactDialogFragment.TAG)
        }
        findViewById<RecyclerView>(R.id.recycler_holder)
            .adapter = contactsAdapter

        findViewById<Button>(R.id.clear_btn).setOnClickListener {
            viewModel.clearAll()
        }

        findViewById<Button>(R.id.add_contact_btn).setOnClickListener {
            ContactDialogFragment.addTypeInstance()
                .show(supportFragmentManager, ContactDialogFragment.TAG)
        }

        viewModel.contactList.observe(this) {
            contactsAdapter.reload(it)
        }

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

   private fun initContacts() {
       val cur = contentResolver.query(
           ContactsContract.Contacts.CONTENT_URI,
           null, null, null, null
       )

       cur.use {
           if (it!!.count > 0) {
               while (it.moveToNext()) {
                   val id: String = it.getString(it.getColumnIndexOrThrow(ContactsContract.Contacts._ID))
                   val name: String =
                       it.getString(it.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))

                   if (it.getString(it.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER))
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
                               pCur.getString(pCur.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
                           val contact = Contact(0,name, phoneNo)
                           viewModel.insertContact(contact)
                       }
                       pCur.close()
                   }
               }
           }
       }

   }

}