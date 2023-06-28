package ru.savenkov.homework.screens.loans

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.savenkov.homework.R
import ru.savenkov.homework.converter.Converter
import ru.savenkov.homework.data.model.Loan
import ru.savenkov.homework.utils.RecyclerDiffUtil

class LoanAdapter(private val onClick: (Long) -> Unit): RecyclerView.Adapter<LoanViewHolder>() {
    var loanList: List<Loan> = emptyList()
        set(value) {
            val difResult = DiffUtil.calculateDiff(RecyclerDiffUtil(field, value))
            field = value
            difResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder =
        LoanViewHolder(parent, onClick)

    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        holder.onBind(loanList[position])
    }

    override fun getItemCount(): Int = loanList.size
}

class LoanViewHolder(parent: ViewGroup, private val onClick: (Long) -> Unit): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.loan_item, parent, false)
)  {

    fun onBind(item: Loan) {

        itemView.findViewById<TextView>(R.id.loan_title).text =
            Converter.toLocalDateTime(item.date)
        itemView.findViewById<TextView>(R.id.loan_status).text =
            Converter.toLocalLanguage(itemView.resources, item.state)
        itemView.findViewById<TextView>(R.id.loan_amount).text =
            itemView.resources.getString(R.string.loan_amount,  item.amount)

        itemView.setOnClickListener {
            onClick.invoke(item.id)
        }
    }
}