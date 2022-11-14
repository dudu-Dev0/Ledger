package com.dudu.ledger.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dudu.ledger.R
import com.dudu.ledger.adapters.LedgerAdapter
import com.dudu.ledger.bean.Ledger
import com.dudu.ledger.widgets.Title
import org.litepal.LitePal
import org.litepal.extension.find

class LedgerFilterActivity : AppCompatActivity() {
    private lateinit var ledger : List<Ledger>
    private lateinit var recyclerView: RecyclerView
    var firstStart = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ledger_filter)
        ledger = intent.getSerializableExtra("ledger") as List<Ledger>
        firstStart = false
        findViewById<TextView>(R.id.filter_title).text = intent.getStringExtra("title_string")
        var amount = 0.0
        for (i in 0 until ledger.size){
            amount += ledger[i].amount
        }
        findViewById<TextView>(R.id.filter_amount_text).text = amount.toString()

        recyclerView= findViewById(R.id.filter_recyclerview)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LedgerAdapter(ledger)
    }
    fun refresh(){
        ledger = LitePal.where("type=?",intent.getStringExtra("title_string")).find()
        var amount = 0.0
        for (i in 0 until ledger.size){
            amount += ledger[i].amount
        }
        findViewById<TextView>(R.id.filter_amount_text).text = amount.toString()
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LedgerAdapter(ledger)
    }

    override fun onResume() {
        super.onResume()
        if (!firstStart){
            refresh()
        }
    }
}