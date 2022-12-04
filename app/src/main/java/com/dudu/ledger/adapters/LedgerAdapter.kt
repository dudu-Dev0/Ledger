package com.dudu.ledger.adapters

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dudu.ledger.utils.AddClickScale
import com.dudu.ledger.utils.MyContext
import com.dudu.ledger.R
import com.dudu.ledger.ui.activities.LedgerDetails
import com.dudu.ledger.ui.activities.MoreOption
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
            val intent = Intent(MyContext.context, LedgerDetails::class.java)
            if((Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)){
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            intent.putExtra("ledger",ledger)
            MyContext.context.startActivity(intent)
        }
        viewHolder.ledgerItem.setOnLongClickListener{
            val position = viewHolder.adapterPosition
            val ledger = ledgerList[position]
            val intent = Intent(MyContext.context, MoreOption::class.java)
            if((Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)){
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            intent.putExtra("ledger",ledger)
            //intent.putExtra("position",position)
            MyContext.context.startActivity(intent)
            return@setOnLongClickListener false
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
        AddClickScale.addClickScale(holder.itemView)
        //holder.itemView.animation = AnimationUtils.loadAnimation(MyContext.context,R.anim.recyclerview_anim_fall_down)
    }
}