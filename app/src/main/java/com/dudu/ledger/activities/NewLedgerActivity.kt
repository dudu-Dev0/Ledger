package com.dudu.ledger.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.dudu.ledger.AddClickScale
import com.dudu.ledger.MyContext
import com.dudu.ledger.R
import com.dudu.ledger.bean.Ledger
import com.dudu.ledger.utils.TimeUtils

class NewLedgerActivity : AppCompatActivity() {
    private lateinit var cost: RadioButton
    private lateinit var income: RadioButton
    private lateinit var amountEditText: EditText
    private lateinit var remarkEditText: EditText
    private lateinit var chooseType: FrameLayout
    private lateinit var typeText: TextView
    private lateinit var finishAddNewLedger: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_ledger)

        income = findViewById(R.id.income_radiobutton)
        cost = findViewById(R.id.cost_radiobutton)
        amountEditText = findViewById(R.id.amount_edittext)
        remarkEditText = findViewById(R.id.remark_edittext)
        chooseType = findViewById(R.id.choose_type)
        typeText = findViewById(R.id.text_type)
        finishAddNewLedger = findViewById(R.id.finish_add_new_ledger)

        initWidgets()

        chooseType.setOnClickListener {
            val intent = Intent(this, TypePicker::class.java)
            startActivityForResult(intent, 1)
        }
        finishAddNewLedger.setOnClickListener {
            if (typeText.text.toString() != "请选择") {
                val ledger = Ledger().apply {
                    isCost = cost.isChecked
                    amount = amountEditText.text.toString().toDouble()
                    type = typeText.text.toString()
                    remark = if (remarkEditText.text.toString() == "") "null" else remarkEditText.text.toString()
                    year = TimeUtils.getYear()
                    month = TimeUtils.getMonth()
                    day = TimeUtils.getDay()
                    hour = TimeUtils.getHour()
                    minute = TimeUtils.getMinute()
                    tick = System.currentTimeMillis()
                }
                ledger.save()
                finish()
            } else {
                Toast.makeText(this, "请选择收支类型", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                typeText.text = data?.getStringExtra("type_return")
            } else {
                Toast.makeText(MyContext.context, "取消选择", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initWidgets() {
        income.buttonDrawable = ColorDrawable(Color.TRANSPARENT)
        cost.buttonDrawable = ColorDrawable(Color.TRANSPARENT)
        income.isChecked = true
        amountEditText.inputType = EditorInfo.TYPE_CLASS_PHONE
        AddClickScale.addClickScale(finishAddNewLedger)
    }
}


