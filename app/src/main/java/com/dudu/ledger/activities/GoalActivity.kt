package com.dudu.ledger.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dudu.ledger.R
import com.dudu.ledger.bean.Ledger
import com.dudu.ledger.utils.TimeUtils
import com.dudu.ledger.widgets.WaveProgress

class GoalActivity : AppCompatActivity() {
    private lateinit var goalText: TextView
    private lateinit var progress: WaveProgress
    private lateinit var targetAmountTextView: TextView
    private lateinit var nowAmountTextView: TextView
    private lateinit var completeGoal: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)

        goalText = findViewById(R.id.my_goal_text)
        progress = findViewById(R.id.wave_progress)
        targetAmountTextView = findViewById(R.id.target_amount_text)
        nowAmountTextView = findViewById(R.id.existing_amount)
        completeGoal = findViewById(R.id.complete_the_goal)

        val prefs = getSharedPreferences("goal", MODE_PRIVATE)
        val goal = prefs.getString("goal", "")
        val target = prefs.getFloat("target_goal_amount", 0F)
        val now = prefs.getFloat("now_amount", 0F)

        goalText.text = goal
        targetAmountTextView.text = target.toString()
        nowAmountTextView.text = now.toString()

        progress.value = now / target * 100
        Log.e("Goal",progress.value.toString())
        if (now/target*100 >= 100) {
            completeGoal.visibility = View.VISIBLE
            completeGoal.setOnClickListener {
                with(getSharedPreferences("goal", MODE_PRIVATE).edit()) {
                    putString("goal", null)
                    putFloat("target_goal_amount", 0f)
                    putFloat("now_amount", now - target)
                    putBoolean("is_existing", false) //true表示计划存在，false则反之
                    apply()
                }
                val ledger = Ledger().apply {
                    isCost = true
                    amount = target.toDouble()
                    type = "零花钱支出"
                    remark = goal
                    year = TimeUtils.getYear()
                    month = TimeUtils.getMonth()
                    day = TimeUtils.getDay()
                    hour = TimeUtils.getHour()
                    minute = TimeUtils.getMinute()
                    tick = System.currentTimeMillis()
                }
                ledger.save()
                Toast.makeText(this, "恭喜，您已完成目标~", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}