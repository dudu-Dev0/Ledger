package com.dudu.ledger.activities

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dudu.ledger.R
import com.dudu.ledger.bean.Ledger
import com.dudu.ledger.utils.TimeUtils
import com.dudu.ledger.widgets.AlwaysMarqueeTextView
import com.dudu.ledger.widgets.PieChartView
import com.dudu.ledger.widgets.PieChartView.ItemType
import org.litepal.LitePal
import org.litepal.extension.find

class CostAnalysis : AppCompatActivity() {
    private lateinit var incomeTextView:AlwaysMarqueeTextView
    private lateinit var costTextView:AlwaysMarqueeTextView
    private lateinit var pieChart:PieChartView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cost_analysis)

        incomeTextView = findViewById(R.id.income_text)
        costTextView = findViewById(R.id.cost_text)
        pieChart = findViewById(R.id.pie_chart)

        val incomeList = LitePal.where("isCost = ? and month = ?","0",TimeUtils.getMonth().toString()).find<Ledger>()
        val costList = LitePal.where("isCost = ? and month = ?","1",TimeUtils.getMonth().toString()).find<Ledger>()

        var incomeAmount : Double = 0.0
        var costAmount : Double = 0.0

        for (i in 0..incomeList.size-1){
            incomeAmount = incomeAmount + incomeList[i].amount
        }
        for (i in 0..costList.size-1){
            costAmount = costAmount + costList[i].amount
        }

        incomeTextView.text = incomeAmount.toString()
        costTextView.text = costAmount.toString()

        val life = LitePal.where("type = ?","生活便利").find<Ledger>()
        val play = LitePal.where("type = ?","文玩娱乐").find<Ledger>()
        val study = LitePal.where("type = ?","学习文具").find<Ledger>()
        val eat = LitePal.where("type = ?","美食吃喝").find<Ledger>()

        var lifeAmount = 0.0
        var playAmount = 0.0
        var studyAmount = 0.0
        var eatAmount = 0.0
        for (i in 0..life.size-1){
            lifeAmount = lifeAmount + life[i].amount
        }
        for (i in 0..play.size-1){
            playAmount = playAmount + play[i].amount
        }
        for (i in 0..study.size-1){
            studyAmount = studyAmount + study[i].amount
        }
        for (i in 0..eat.size-1){
            eatAmount = eatAmount + eat[i].amount
        }

        pieChart.addItemType(ItemType("生活便利", lifeAmount.toFloat(),getRandomColor()))
        pieChart.addItemType(ItemType("文玩娱乐", playAmount.toFloat(),getRandomColor()))
        pieChart.addItemType(ItemType("学习文具", studyAmount.toFloat(),getRandomColor()))
        pieChart.addItemType(ItemType("美食吃喝", eatAmount.toFloat(),getRandomColor()))
    }
    fun getRandomColor():Int{
        val color = arrayOf(Color.BLUE,Color.CYAN,Color.GRAY,Color.GREEN,Color.RED,Color.YELLOW).random()
        return color
    }
}