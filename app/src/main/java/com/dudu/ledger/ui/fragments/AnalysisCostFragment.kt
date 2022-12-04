package com.dudu.ledger.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dudu.ledger.R
import com.dudu.ledger.adapters.AnalysisTypeWithProgressAdapter
import com.dudu.ledger.bean.AnalysisProgress
import com.dudu.ledger.bean.Ledger
import com.dudu.ledger.ui.widgets.AlwaysMarqueeTextView
import com.dudu.ledger.utils.Constants
import com.dudu.ledger.utils.TimeUtils
import org.litepal.LitePal
import org.litepal.extension.find

class AnalysisCostFragment : Fragment() {
    private lateinit var costTextView: AlwaysMarqueeTextView
    private lateinit var recyclerView: RecyclerView
    private var list = mutableListOf<AnalysisProgress>()
    private var repeatedPosition = 0
    private var costAmount = 0.0
    private var costList =
        LitePal.where("isCost = ? and month = ?", "1", TimeUtils.getMonth().toString()).find<Ledger>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_analysis_cost,null)
        costTextView = view.findViewById(R.id.cost_text)
        recyclerView = view.findViewById(R.id.cost_analysis_recyclerview)

        costList =
            LitePal.where("isCost = ? and month = ?", "1", TimeUtils.getMonth().toString()).find<Ledger>()

        for (i in 0 until costList.size) {
            costAmount += costList[i].amount
        }
        initCostRecyclerView()
        return view
    }
    fun checkList(list: List<AnalysisProgress>, item: AnalysisProgress): Boolean {
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
    fun initCostRecyclerView() {
        repeatedPosition = 0
        list = mutableListOf<AnalysisProgress>()
        for (i in 0 until Constants.costTypeList.size) {
            var costType = Constants.costTypeList[i]
            Log.e("", "for *1 ")
            for (i in 0 until costList.size) {
                var costAnalysis = 0
                if (costType.text == costList[i].type) {
                    val analysisProgress = AnalysisProgress()
                    analysisProgress.typeText = costType.typeText         //这段写的有亿点屎，慎看（，能跑就对了
                    costAnalysis += costList[i].amount.toInt()
                    //if (i == incomeList.size-1) {
                    analysisProgress.progress = (costAnalysis.toFloat() / costAmount).toFloat() * 100
                    if (!checkList(list, analysisProgress)) list.add(analysisProgress)
                    else {
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
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        list.sortByDescending { analysisProgress: AnalysisProgress -> analysisProgress.progress }
        recyclerView.adapter = AnalysisTypeWithProgressAdapter(list, requireActivity())

    }
    fun refresh(){
        repeatedPosition = 0
        costAmount = 0.0
        costList =
            LitePal.where("isCost = ? and month = ?", "1", TimeUtils.getMonth().toString()).find<Ledger>()
        list = mutableListOf<AnalysisProgress>()
        for (i in 0 until costList.size) {
            costAmount += costList[i].amount
        }

        costTextView.text = costAmount.toString()

        initCostRecyclerView()
    }
    override fun onResume() {
        super.onResume()
        refresh()
    }
}