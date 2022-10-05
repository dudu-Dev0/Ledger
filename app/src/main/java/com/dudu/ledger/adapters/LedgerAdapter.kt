package com.dudu.ledger.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dudu.ledger.MyContext
import com.dudu.ledger.R
import com.dudu.ledger.activities.LedgerDetails
import com.dudu.ledger.bean.Ledger

class LedgerAdapter(val ledgerList: List<Ledger>) : RecyclerView.Adapter<LedgerAdapter.ViewHolder>(){
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val ledgerItem:FrameLayout = view.findViewById(R.id.ledger_item)
        val ledgerSort:TextView = view.findViewById(R.id.ledger_sort)
        val ledgerSortImage:ImageView = view.findViewById(R.id.ledger_sort_image)
        val ledgerAmount:TextView = view.findViewById(R.id.ledger_amount)
        val ledgerDate:TextView = view.findViewById(R.id.ledger_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ledger_item,parent,false)
        val viewHolder = ViewHolder(view)
        viewHolder.ledgerItem.setOnClickListener {
            val position = viewHolder.adapterPosition
            val ledger = ledgerList[position]
            val intent = Intent(MyContext.context,LedgerDetails::class.java)
            intent.putExtra("ledger",ledger)
            MyContext.context.startActivity(intent)
        }
        return viewHolder
    }

    override fun getItemCount() = ledgerList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ledger = ledgerList[position]
        if(ledger.isCost){
            holder.ledgerSort.text = "支出"
            holder.ledgerSortImage.setImageResource(R.drawable.cost)
        }else{
            holder.ledgerSort.text = "收入"
            holder.ledgerSortImage.setImageResource(R.drawable.income)
        }
        holder.ledgerAmount.text = "${ledger.amount}元"
        holder.ledgerDate.text = "${ledger.year}年${ledger.month}月${ledger.day}日${ledger.hour}时${ledger.minute}分"
    }
}