package com.dudu.ledger.activities

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.dudu.ledger.ActivityCollector
import com.dudu.ledger.AddClickScale
import com.dudu.ledger.R
import com.dudu.ledger.bean.Ledger
import org.litepal.LitePal
import org.litepal.extension.deleteAll

class DoYouReallyWantToDelete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_do_you_really_want_to_delete)

        val ledger = intent.getSerializableExtra("ledger") as Ledger

        val delete: ImageButton = findViewById(R.id.confirm_delete)
        val cancel: Button = findViewById(R.id.cancel_del)

        AddClickScale.addClickScale(delete)
        AddClickScale.addClickScale(cancel)

        delete.setOnClickListener {
            LitePal.deleteAll<Ledger>("tick = ?",ledger.tick.toString())
            ActivityCollector.finishAllWithNoAnim()
            finish()
            overridePendingTransition(0,0)
        }

        cancel.setOnClickListener {
            finishAfterTransition()
        }
    }
}