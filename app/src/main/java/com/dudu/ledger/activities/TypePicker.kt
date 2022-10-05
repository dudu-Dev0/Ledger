package com.dudu.ledger.activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.dudu.ledger.AddClickScale
import com.dudu.ledger.R


class TypePicker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_picker)

        val radioGroup: RadioGroup = findViewById(R.id.type_picker_radio_group)
        val radioButtonLife: RadioButton = findViewById(R.id.radiobutton_life)
        val radioButtonPlay: RadioButton = findViewById(R.id.radiobutton_play)
        val radioButtonStudy: RadioButton = findViewById(R.id.radiobutton_study)
        val radioButtonEat: RadioButton = findViewById(R.id.radiobutton_eat)

        AddClickScale.addClickScale(radioButtonLife)
        AddClickScale.addClickScale(radioButtonPlay)
        AddClickScale.addClickScale(radioButtonStudy)
        AddClickScale.addClickScale(radioButtonEat)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val intent = Intent()
            when (checkedId) {
                R.id.radiobutton_life -> {
                    intent.putExtra("type_return", "生活便利")
                    setResult(RESULT_OK, intent)
                    finish()
                }

                R.id.radiobutton_play -> {
                    intent.putExtra("type_return", "文玩娱乐")
                    setResult(RESULT_OK, intent)
                    finish()
                }

                R.id.radiobutton_study -> {
                    intent.putExtra("type_return", "学习文具")
                    setResult(RESULT_OK, intent)
                    finish()
                }

                R.id.radiobutton_eat -> {
                    intent.putExtra("type_return", "美食吃喝")
                    setResult(RESULT_OK, intent)
                    finish()
                }

                else -> Log.d(TAG, "wdnmd????")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        setResult(RESULT_CANCELED)
    }
}