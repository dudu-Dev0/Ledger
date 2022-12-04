package com.dudu.ledger.ui.widgets

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import com.dudu.ledger.utils.MyContext
import com.dudu.ledger.R
import com.dudu.ledger.ui.activities.Menu
import com.dudu.ledger.ui.activities.NewLedgerActivity

class Title(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    var ta = context.obtainStyledAttributes(attrs, R.styleable.Title)
    var text = ta.getString(R.styleable.Title_titleText)
    val buttonVis = ta.getString(R.styleable.Title_buttonVis)
    init {
        ta = context.obtainStyledAttributes(attrs, R.styleable.Title)
        LayoutInflater.from(context).inflate(R.layout.watch_title, this)
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