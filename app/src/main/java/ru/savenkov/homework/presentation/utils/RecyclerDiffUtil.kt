package ru.savenkov.homework

import androidx.recyclerview.widget.DiffUtil
import ru.savenkov.homework.data.Contact

class RecyclerDiffUtil(
    private val oldList: List<Contact>,
    private val newList: List<Contact>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}