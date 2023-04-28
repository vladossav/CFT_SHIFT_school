package ru.savenkov.homework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter(val clickListener: Listener) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {
    private var contactsList: MutableList<Contact> = mutableListOf()

    interface Listener {
        fun onClick(id:Int, name: String, phone: String)
    }

    inner class ContactViewHolder(item: View): RecyclerView.ViewHolder(item) {
        var id: Int = 0
        val name: TextView = item.findViewById(R.id.name_data)
        val phone: TextView = item.findViewById(R.id.phone_data)
        init {
            item.setOnClickListener {
                clickListener.onClick(id, name.text.toString(), phone.text.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.name.text = contactsList[position].name
        holder.phone.text = contactsList[position].phone
        holder.id = contactsList[position].id
    }

    override fun getItemCount(): Int = contactsList.size


    fun reload(newList: MutableList<Contact>) {
        val difResult = DiffUtil.calculateDiff(RecyclerDiffUtil(contactsList, newList))
        contactsList = newList
        difResult.dispatchUpdatesTo(this)
    }
}