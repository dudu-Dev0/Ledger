package com.dudu.ledger.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dudu.ledger.R
import com.dudu.ledger.adapters.LedgerAdapter
import com.dudu.ledger.bean.Ledger
import com.yan.pullrefreshlayout.PullRefreshLayout
import org.litepal.LitePal
import org.litepal.extension.findAll
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var ledgerList:List<Ledger>
    private lateinit var ledgerRecyclerView: RecyclerView   //数据显示
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ledgerRecyclerView = findViewById(R.id.recycler_main_ledger)

        initData()
        println(LitePal.findAll<Ledger>())
    }

    override fun onRestart() {
        super.onRestart()
        initData()
    }
    private fun initData(){
        LitePal.getDatabase()
        ledgerList = LitePal.findAll<Ledger>()
        Collections.reverse(ledgerList)
        ledgerRecyclerView.layoutManager = LinearLayoutManager(this)
        ledgerRecyclerView.setAdapter(LedgerAdapter(ledgerList))
    }
}