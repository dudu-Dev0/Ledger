package com.dudu.ledger.activities

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.dudu.ledger.MyContext
import com.dudu.ledger.R

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val costAnalysis : LinearLayout = findViewById(R.id.cost_analysis_menu_button)

        costAnalysis.setOnClickListener {
            val intent = Intent(MyContext.context, CostAnalysis::class.java)
            startActivity(intent)
        }
    }
}