package ru.savenkov.homework

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: List<ListItem> = emptyList()

    private companion object {
        const val STUDENT_VIEW_TYPE = 0
        const val BANNER_VIEW_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            STUDENT_VIEW_TYPE -> StudentViewHolder(parent)
            BANNER_VIEW_TYPE -> BannerViewHolder(parent)
            else -> throw IllegalArgumentException("Wrong viewType: $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StudentViewHolder -> holder.bind(list[position])
            is BannerViewHolder -> holder.bind(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int =
        if (list[position] is ListItem.StudentItem) {
            STUDENT_VIEW_TYPE
        } else {
            BANNER_VIEW_TYPE
        }
}

class StudentViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
) {

    fun bind(item: ListItem) {
        val name = "${(item as ListItem.StudentItem).secondName} ${item.name}"
        itemView.findViewById<TextView>(R.id.student_name).text = name
        itemView.findViewById<TextView>(R.id.student_description).text = item.description
        itemView.findViewById<ImageView>(R.id.arrow_image).isVisible = item.hasPortfolio
    }
}

class BannerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.banner_item, parent, false)
) {

    fun bind(item: ListItem) {
        itemView.findViewById<TextView>(R.id.banner_title).text = (item as ListItem.BannerItem).title
        itemView.findViewById<TextView>(R.id.banner_description).text = item.description
    }
}
