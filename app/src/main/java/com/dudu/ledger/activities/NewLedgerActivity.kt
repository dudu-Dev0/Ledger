package com.dudu.ledger.activities

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.dudu.ledger.AddClickScale
import com.dudu.ledger.R
import com.dudu.ledger.bean.Ledger
import com.dudu.ledger.utils.TimeUtils
import java.sql.Time

class NewLedgerActivity : AppCompatActivity() {
    private lateinit var cost:RadioButton
    private lateinit var income:RadioButton
    private lateinit var amountEditText: EditText
    private lateinit var chooseType:FrameLayout
    private lateinit var typeText:TextView
    private lateinit var finishAddNewLedger:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_ledger)

        income = findViewById(R.id.income_radiobutton)
        cost = findViewById(R.id.cost_radiobutton)
        amountEditText = findViewById(R.id.amount_edittext)
        chooseType = findViewById(R.id.choose_type)
        typeText = findViewById(R.id.text_type)
        finishAddNewLedger = findViewById(R.id.finish_add_new_ledger)

        initWidgets()

        chooseType.setOnClickListener {

        }
        finishAddNewLedger.setOnClickListener {
            if(typeText.text.toString() == "请选择") {
                val ledger = Ledger().apply {
                    isCost = cost.isChecked
                    amount = amountEditText.text.toString().toDouble()
                    type = typeText.text.toString()
                    remark = "null"
                    year = TimeUtils.getYear()
                    month = TimeUtils.getMonth()
                    day = TimeUtils.getDay()
                    hour = TimeUtils.getHour()
                    minute = TimeUtils.getMinute()
                }
                ledger.save()
                finish()
            }else{
                Toast.makeText(this,"请选择收支类型",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun initWidgets(){
        income.buttonDrawable = ColorDrawable(Color.TRANSPARENT)
        cost.buttonDrawable = ColorDrawable(Color.TRANSPARENT)
        income.isChecked = true
        amountEditText.inputType = EditorInfo.TYPE_CLASS_PHONE
        AddClickScale.addClickScale(finishAddNewLedger)
    }
}


