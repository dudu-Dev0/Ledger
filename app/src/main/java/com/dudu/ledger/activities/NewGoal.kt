package com.dudu.ledger.activities

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dudu.ledger.R

class NewGoal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_goal)

        val yourGoalEdittext: EditText = findViewById(R.id.your_goal_edittext)
        val goalAmountEdittext: EditText = findViewById(R.id.goal_amount_edittext)
        val finishAddNewGoal: Button = findViewById(R.id.finish_add_new_goal)

        goalAmountEdittext.inputType = EditorInfo.TYPE_CLASS_PHONE

        finishAddNewGoal.setOnClickListener {
            val text = goalAmountEdittext.text.toString()
            if (isNumeric(text)) {
                with(getSharedPreferences("goal", MODE_PRIVATE).edit()) {
                    putString("goal", yourGoalEdittext.text.toString())
                    putFloat("target_goal_amount", goalAmountEdittext.text.toString().toFloat())
                    putBoolean("is_existing", true) //true表示计划存在，false则反之
                    apply()
                }
                val intent = Intent(this, GoalActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this,"请输入大于零的整数",Toast.LENGTH_SHORT).show()
            }
        }
    }


    /**
     * @param str
     * @function 判断输入的数据是否是大于等于零的整数
     */
    private fun isNumeric(str: String): Boolean {
        var i = str.length
        while (--i >= 0) {
            if (!Character.isDigit(str[i])) {
                return false
            }
        }
        if (str.toFloat()<=0){
            return false
        }
        return true
    }
}