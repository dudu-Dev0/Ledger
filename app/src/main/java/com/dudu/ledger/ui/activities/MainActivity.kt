package com.dudu.ledger.ui.activities

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dudu.ledger.R
import com.dudu.ledger.adapters.LedgerAdapter
import com.dudu.ledger.bean.Ledger
import org.litepal.LitePal
import org.litepal.extension.findAll
import java.util.*

class MainActivity : AppCompatActivity() {
    private var ledgerList: List<Ledger> = mutableListOf()
    private lateinit var ledgerRecyclerView: RecyclerView   //数据显示
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ledgerRecyclerView = findViewById(R.id.recycler_main_ledger)
        initData()
    }


    private fun initData() {
        LitePal.getDatabase()
        val tmpList  = LitePal.findAll<Ledger>()
        tmpList.reverse()
        if (ledgerList != tmpList ) {
            ledgerList = LitePal.findAll<Ledger>()
            Collections.reverse(ledgerList)
            ledgerRecyclerView.layoutManager = LinearLayoutManager(this)
            ledgerRecyclerView.adapter = LedgerAdapter(ledgerList)
        }
    }

    override fun onResume() {
        super.onResume()
        initData()
    }
}