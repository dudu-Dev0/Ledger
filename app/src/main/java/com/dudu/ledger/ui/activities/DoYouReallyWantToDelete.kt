package com.dudu.ledger.ui.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.dudu.ledger.utils.ActivityCollector
import com.dudu.ledger.utils.AddClickScale
import com.dudu.ledger.R
import com.dudu.ledger.bean.Ledger
import org.litepal.LitePal
import org.litepal.extension.deleteAll

class DoYouReallyWantToDelete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_do_you_really_want_to_delete)

        val ledger = intent.getSerializableExtra("ledger") as Ledger

        val delete: Button = findViewById(R.id.confirm_delete)
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