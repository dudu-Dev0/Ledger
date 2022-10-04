package com.dudu.ledger.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dudu.ledger.R
import com.dudu.ledger.bean.Ledger

class LedgerAdapter(val ledgerList: List<Ledger>) : RecyclerView.Adapter<LedgerAdapter.ViewHolder>(){
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val ledgerSort:TextView = view.findViewById(R.id.ledger_sort)
        val ledgerSortImage:ImageView = view.findViewById(R.id.ledger_sort_image)
        val ledgerAmount:TextView = view.findViewById(R.id.ledger_amount)
        val ledgerDate:TextView = view.findViewById(R.id.ledger_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ledger_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = ledgerList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ledger = ledgerList[position]
        if(ledger.isCost){
            holder.ledgerSort.setText("支出")
            holder.ledgerSortImage.setImageResource(R.drawable.cost)
        }else{
            holder.ledgerSort.setText("收入")
            holder.ledgerSortImage.setImageResource(R.drawable.income)
        }
        holder.ledgerAmount.text = "${ledger.amount}元"
        holder.ledgerDate.text = "${ledger.year}年${ledger.month}月${ledger.day}日${ledger.hour}时${ledger.minute}分"
    }
}