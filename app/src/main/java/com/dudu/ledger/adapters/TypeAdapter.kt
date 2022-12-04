package com.dudu.ledger.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dudu.ledger.utils.AddClickScale
import com.dudu.ledger.R
import com.dudu.ledger.bean.Type

class TypeAdapter (val typeList: List<Type>,val activity: Activity) : RecyclerView.Adapter<TypeAdapter.ViewHolder>(){
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val img : ImageView = view.findViewById(R.id.type_img)
        val text : TextView = view.findViewById(R.id.type_text)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.type_item,parent,false)
        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            val intent = Intent()
            intent.putExtra("type_return",viewHolder.text.text)
            activity.setResult(Activity.RESULT_OK,intent)
            activity.finish()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = typeList[position]
        AddClickScale.addClickScale(holder.itemView)
        holder.img.setImageResource(type.picture)
        holder.text.text = type.typeText
    }

    override fun getItemCount() = typeList.size

}