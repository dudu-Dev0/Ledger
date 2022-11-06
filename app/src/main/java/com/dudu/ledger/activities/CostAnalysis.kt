package com.dudu.ledger.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dudu.ledger.Constants
import com.dudu.ledger.R
import com.dudu.ledger.adapters.AnalysisTypeWithProgressAdapter
import com.dudu.ledger.bean.AnalysisProgress
import com.dudu.ledger.bean.Ledger
import com.dudu.ledger.utils.TimeUtils
import com.dudu.ledger.widgets.AlwaysMarqueeTextView
import org.litepal.LitePal
import org.litepal.extension.find


class CostAnalysis : AppCompatActivity() {
    private lateinit var incomeTextView:AlwaysMarqueeTextView
    private lateinit var costTextView:AlwaysMarqueeTextView
    private lateinit var recyclerView: RecyclerView
    private var repeatedPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cost_analysis)

        incomeTextView = findViewById(R.id.income_text)
        costTextView = findViewById(R.id.cost_text)
        recyclerView = findViewById(R.id.cost_analysis_recyclerview)

        val incomeList = LitePal.where("isCost = ? and month = ?","0",TimeUtils.getMonth().toString()).find<Ledger>()
        val costList = LitePal.where("isCost = ? and month = ?","1",TimeUtils.getMonth().toString()).find<Ledger>()

        var incomeAmount = 0.0
        var costAmount = 0.0

        for (i in 0 until incomeList.size){
            incomeAmount += incomeList[i].amount
        }
        for (i in 0 until costList.size){
            costAmount += costList[i].amount
        }

        incomeTextView.text = incomeAmount.toString()
        costTextView.text = costAmount.toString()

        var list  = mutableListOf<AnalysisProgress>()

        var position = 0

        for(i in 0 until Constants.incomeTypeList.size){
            var incomeType = Constants.incomeTypeList[i]
            Log.e("","for *1 ")
            for (i in 0 until incomeList.size){
                var incomeAnalysis = 0
                if (incomeType.text == incomeList[i].type){
                    val analysisProgress = AnalysisProgress()
                    analysisProgress.typeText = incomeType.typeText
                    incomeAnalysis += incomeList[i].amount.toInt()
                    //if (i == incomeList.size-1) {
                    analysisProgress.progress = (incomeAnalysis.toFloat() / incomeAmount).toFloat()*100
                    if (!checkList(list,analysisProgress)) list.add(analysisProgress)
                    else{
                        analysisProgress.progress = analysisProgress.progress + list[repeatedPosition].progress
                        list[repeatedPosition] = analysisProgress
                        repeatedPosition = 0
                    }
                    //}

                }
            }
        }
        /*val a = AnalysisProgress()
        a.progress = 50F
        a.typeText = "111"
        list.add(a)*/
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AnalysisTypeWithProgressAdapter(list,this)
        Log.e("",list.size.toString())
    }
    fun checkList(list: List<AnalysisProgress> ,item: AnalysisProgress) : Boolean {
        var returnData = false
        for (i in 0 until list.size) {
            if (list[i].typeText == item.typeText) {
                returnData = true
                repeatedPosition = i
                break
            } else {
                returnData = false
                break
            }
        }
        return returnData
    }
}

