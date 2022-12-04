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
import com.dudu.ledger.utils.MyContext
import com.dudu.ledger.utils.TimeUtils
import org.litepal.LitePal
import org.litepal.extension.find


class AnalysisIncomeFragment : Fragment() {
    private lateinit var incomeTextView: AlwaysMarqueeTextView
    private lateinit var recyclerView: RecyclerView
    private var incomeList =
        LitePal.where("isCost = ? and month = ?", "0", TimeUtils.getMonth().toString()).find<Ledger>()
    private var list = mutableListOf<AnalysisProgress>()
    private var repeatedPosition = 0
    private var incomeAmount = 0.0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_analysis_income,null)
        incomeTextView = view.findViewById(R.id.income_text)
        recyclerView = view.findViewById(R.id.income_analysis_recyclerview)

        incomeList =
            LitePal.where("isCost = ? and month = ?", "0", TimeUtils.getMonth().toString()).find<Ledger>()

        for (i in 0 until incomeList.size) {
            incomeAmount += incomeList[i].amount
        }
        incomeTextView.text = incomeAmount.toString()

        Log.e("",incomeAmount.toString())

        initIncomeRecyclerView()
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

    fun initIncomeRecyclerView() {
        repeatedPosition = 0
        list = mutableListOf<AnalysisProgress>()
        for (i in 0 until Constants.incomeTypeList.size) {
            var incomeType = Constants.incomeTypeList[i]
            Log.e("", "for *1 ")
            for (i in 0 until incomeList.size) {
                var incomeAnalysis = 0
                if (incomeType.text == incomeList[i].type) {
                    val analysisProgress = AnalysisProgress()
                    analysisProgress.typeText = incomeType.typeText         //这段写的有亿点屎，慎看（，能跑就对了
                    incomeAnalysis += incomeList[i].amount.toInt()
                    //if (i == incomeList.size-1) {
                    analysisProgress.progress = (incomeAnalysis.toFloat() / incomeAmount).toFloat() * 100
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
        incomeAmount = 0.0
        incomeList =
            LitePal.where("isCost = ? and month = ?", "0", TimeUtils.getMonth().toString()).find<Ledger>()
        list = mutableListOf<AnalysisProgress>()
        for (i in 0 until incomeList.size) {
            incomeAmount += incomeList[i].amount
        }

        incomeTextView.text = incomeAmount.toString()

        initIncomeRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        refresh()
    }
}