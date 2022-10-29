package com.dudu.ledger.activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.dudu.ledger.ActivityCollector
import com.dudu.ledger.AddClickScale
import com.dudu.ledger.R
import com.dudu.ledger.bean.Ledger

class MoreOption : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_option)

        val screenshot: ImageButton = findViewById(R.id.screenshot)
        val delete: ImageButton = findViewById(R.id.delete)
        val back: Button = findViewById(R.id.back)

        ActivityCollector.addActivity(this)

        screenshot.scaleX = 0F
        screenshot.scaleY = 0F
        delete.scaleX = 0F
        delete.scaleY = 0F

        screenshot.animate()
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(300)
            .interpolator = OvershootInterpolator()
        delete.animate()
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(300)
            .interpolator = OvershootInterpolator()

        AddClickScale.addClickScale(screenshot)
        AddClickScale.addClickScale(delete)

        val ledger = intent.getSerializableExtra("ledger") as Ledger
        //val position = intent.getIntExtra("position",0)

        screenshot.setOnClickListener {

        }
        delete.setOnClickListener {
            intent = Intent(this,DoYouReallyWantToDelete::class.java)
            intent.putExtra("ledger",ledger)
            startActivity(intent)
        }
        back.setOnClickListener {
            finish()
            overridePendingTransition(0,0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.rmActivity(this)
    }
}