package com.dudu.ledger.adapters

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dudu.ledger.R
import com.dudu.ledger.bean.Ledger
import java.util.stream.Collectors
import kotlin.concurrent.thread

class DateLedgerAdapter(val ledgerList: List<Ledger>,val activity: Activity) : RecyclerView.Adapter<DateLedgerAdapter.ViewHolder>(){
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val dateText :TextView = view.findViewById(R.id.date_text)
        val recyclerView:RecyclerView = view.findViewById(R.id.date_ledger_recycler)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.date_ledger_item,parent,false)
        val viewHolder = ViewHolder(view)

        return viewHolder
    }

    override fun getItemCount(): Int {
        var size = 0
        var date = ""
        for (i in 0 until ledgerList.size) {
            var ledger = ledgerList[i]
            if (date != "${ledger.year}年${ledger.month}月") {
                date = "${ledger.year}年${ledger.month}月"
                size++
            }
        }
        return size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}
