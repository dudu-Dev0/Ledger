package com.dudu.ledger.adapters

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar
import com.dudu.ledger.AddClickScale
import com.dudu.ledger.MyContext
import com.dudu.ledger.R
import com.dudu.ledger.bean.AnalysisProgress
import com.dudu.ledger.bean.Type
import de.hdodenhof.circleimageview.CircleImageView

class AnalysisTypeWithProgressAdapter (val analysisList: List<AnalysisProgress>, val activity: Activity) : RecyclerView.Adapter<AnalysisTypeWithProgressAdapter.ViewHolder>(){
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val progress : IconRoundCornerProgressBar = view.findViewById(R.id.analysis_progressbar)
        val type : TextView = view.findViewById(R.id.text_type_analysis)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysisTypeWithProgressAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cost_analysis_progress_item,parent,false)
        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {

        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val analysis = analysisList[position]
        AddClickScale.addClickScale(holder.itemView)

        holder.progress.progress = analysis.progress
        holder.type.text = analysis.typeText
    }

    override fun getItemCount() = analysisList.size

}