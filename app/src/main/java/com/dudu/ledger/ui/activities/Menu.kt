package com.dudu.ledger.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.dudu.ledger.utils.MyContext
import com.dudu.ledger.R

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val costAnalysis : LinearLayout = findViewById(R.id.cost_analysis_menu_button)
        val goal : LinearLayout = findViewById(R.id.goal)
        val search : LinearLayout = findViewById(R.id.search)

        costAnalysis.setOnClickListener {
            val intent = Intent(MyContext.context, CostAnalysis::class.java)
            startActivity(intent)
        }
        goal.setOnClickListener {
            var intent:Intent = if (getSharedPreferences("goal", MODE_PRIVATE).getBoolean("is_existing",false)) Intent(
                MyContext.context, GoalActivity::class.java)
            else Intent(this, NewGoal::class.java)
            startActivity(intent)
        }
        search.setOnClickListener {
            var intent = Intent(MyContext.context, SearchActivity::class.java)
            startActivity(intent)
        }
    }
}