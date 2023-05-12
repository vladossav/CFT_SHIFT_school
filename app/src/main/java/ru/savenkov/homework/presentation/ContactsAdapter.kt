package ru.savenkov.homework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.savenkov.homework.data.Contact

class ContactsAdapter(private val onClick: (Contact) -> Unit) : RecyclerView.Adapter<ContactViewHolder>() {
    private var contactsList: List<Contact> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ContactViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactsList[position])
    }

    override fun getItemCount(): Int = contactsList.size

    fun reload(newList: List<Contact>) {
        val difResult = DiffUtil.calculateDiff(RecyclerDiffUtil(contactsList, newList))
        contactsList = newList
        difResult.dispatchUpdatesTo(this)
    }
}

class ContactViewHolder(item: View, private val onClick: (Contact) -> Unit): RecyclerView.ViewHolder(item) {
    var id: Int = 0
    val name: TextView = item.findViewById(R.id.name_data)
    val phone: TextView = item.findViewById(R.id.phone_data)

    fun bind(contact: Contact) {
        name.text = contact.name
        phone.text = contact.phone
        id = contact.id
        itemView.setOnClickListener {
            onClick(contact)
        }
    }
}