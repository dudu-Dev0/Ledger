package com.dudu.ledger.adapters

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import com.dudu.ledger.utils.AddClickScale
import com.dudu.ledger.utils.MyContext
import com.dudu.ledger.R
import com.dudu.ledger.ui.activities.LedgerFilterActivity
import com.dudu.ledger.bean.AnalysisProgress
import com.dudu.ledger.bean.Ledger
import com.dudu.ledger.utils.Constants
import org.litepal.LitePal
import org.litepal.extension.find
import java.io.Serializable

class AnalysisTypeWithProgressAdapter (val analysisList: List<AnalysisProgress>, val activity: Activity) : RecyclerView.Adapter<AnalysisTypeWithProgressAdapter.ViewHolder>(){
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val progress : RoundCornerProgressBar = view.findViewById(R.id.analysis_progressbar)
        val type : TextView = view.findViewById(R.id.text_type_analysis)
        val progressText :TextView = view.findViewById(R.id.analysis_progress_text)
        val typeImg :ImageView = view.findViewById(R.id.type_img)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysisTypeWithProgressAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cost_analysis_progress_item,parent,false)
        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(MyContext.context, LedgerFilterActivity::class.java)
            if((Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)){
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            intent.putExtra("ledger",(LitePal.where("type=?",viewHolder.type.text.toString()).find<Ledger>()) as Serializable)
            intent.putExtra("title_string", viewHolder.type.text.toString())
            Log.e("debug",LitePal.toString())
            MyContext.context.startActivity(intent)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val analysis = analysisList[position]
        AddClickScale.addClickScale(holder.itemView)

        holder.progress.progress = analysis.progress
        holder.type.text = analysis.typeText
        for (i in Constants.costTypeList){
            if (i.typeText == analysis.typeText){
                holder.typeImg.background = MyContext.context.getDrawable(i.pic)
            }
        }
        for (i in Constants.incomeTypeList){
            if (i.typeText == analysis.typeText){
                holder.typeImg.background = MyContext.context.getDrawable(i.pic)
            }
        }
        "%.1f".format(analysis.progress)
        holder.progressText.text = "${"%.1f".format(analysis.progress)}%"
    }

    override fun getItemCount() = analysisList.size


}

