package com.dudu.ledger.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
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
    private lateinit var chooseTypeText: TextView
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
        chooseTypeText = findViewById(R.id.choose_type_text)
        chooseType = findViewById(R.id.choose_type)
        typeText = findViewById(R.id.text_type)
        finishAddNewLedger = findViewById(R.id.finish_add_new_ledger)

        initWidgets()

        cost.setOnCheckedChangeListener { buttonView, isChecked ->
            typeText.text = "请选择"
        }

        chooseType.setOnClickListener {
            val intent = Intent(this, TypePicker::class.java)
            intent.putExtra("isCost",cost.isChecked)
            startActivityForResult(intent, 1)
        }
        finishAddNewLedger.setOnClickListener {
            if (isNumeric(amountEditText.text.toString())) {
                if (typeText.text.toString() != "请选择"||chooseType.visibility == View.GONE) {
                    val ledger = Ledger().apply {
                        isCost = cost.isChecked
                        amount = amountEditText.text.toString().toDouble()
                        type = if (chooseType.visibility != View.GONE) typeText.text.toString() else ""
                        remark = if (remarkEditText.text.toString() == "") "无~" else remarkEditText.text.toString()
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
            }else{
                Toast.makeText(this, "请输入大于零的整数", Toast.LENGTH_LONG).show()
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


