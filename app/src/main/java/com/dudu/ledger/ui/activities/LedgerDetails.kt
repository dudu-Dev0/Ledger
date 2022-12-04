package com.dudu.ledger.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dudu.ledger.R
import com.dudu.ledger.bean.Ledger

class LedgerDetails : AppCompatActivity() {
    private lateinit var date: TextView
    private lateinit var isCost: TextView
    private lateinit var amount: TextView
    private lateinit var type: TextView
    private lateinit var remark: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ledger_details)

        date = findViewById(R.id.detail_date)
        isCost = findViewById(R.id.detail_is_cost)
        amount = findViewById(R.id.detail_amount)
        type = findViewById(R.id.detail_type)
        remark = findViewById(R.id.detail_remark)

        val ledger = intent.getSerializableExtra("ledger") as Ledger

        date.text = "${ledger.year}年${ledger.month}月${ledger.day}日${ledger.hour}时${ledger.minute}分"
        isCost.text = if (ledger.isCost) "支出" else "收入"
        amount.text = ledger.amount.toString()
        type.text = ledger.type
        remark.text = ledger.remark
    }
}