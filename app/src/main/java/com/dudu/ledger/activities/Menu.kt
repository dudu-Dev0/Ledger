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
        val goal : LinearLayout = findViewById(R.id.goal)

        costAnalysis.setOnClickListener {
            val intent = Intent(MyContext.context, CostAnalysis::class.java)
            startActivity(intent)
        }
        goal.setOnClickListener {
            var intent:Intent = if (getSharedPreferences("goal", MODE_PRIVATE).getBoolean("is_existing",false)) Intent(MyContext.context, GoalActivity::class.java)
            else Intent(this,NewGoal::class.java)
            startActivity(intent)
        }
    }
}