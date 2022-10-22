package com.dudu.ledger.widgets

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import com.dudu.ledger.MyContext
import com.dudu.ledger.R
import com.dudu.ledger.activities.Menu
import com.dudu.ledger.activities.NewLedgerActivity

class Title(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.watch_title, this)
        val ta = context.obtainStyledAttributes(attrs, R.styleable.Title)
        val text = ta.getString(R.styleable.Title_titleText)
        val buttonVis = ta.getString(R.styleable.Title_buttonVis)
        ta.recycle()
        val titleText = findViewById<TextView>(R.id.title_text)
        val titleMenuButton = findViewById<ImageButton>(R.id.title_button_menu)
        val titleNewButton = findViewById<ImageButton>(R.id.title_button_new)
        titleText.text = text
        titleMenuButton.setOnClickListener {
            val intent = Intent(MyContext.context, Menu::class.java)
            if((Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)){
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            MyContext.context.startActivity(intent)
        }
        titleNewButton.setOnClickListener {
            val intent = Intent(MyContext.context, NewLedgerActivity::class.java)
            if((Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)){
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            MyContext.context.startActivity(intent)
        }
        if (buttonVis == "VISIBLE") {
            titleMenuButton.visibility = VISIBLE
            titleNewButton.visibility = VISIBLE
        } else {
            titleMenuButton.visibility = GONE
            titleNewButton.visibility = GONE
        }
    }
}