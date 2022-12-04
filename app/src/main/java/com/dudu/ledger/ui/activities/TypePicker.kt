package com.dudu.ledger.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dudu.ledger.utils.Constants
import com.dudu.ledger.R
import com.dudu.ledger.adapters.TypeAdapter


class TypePicker : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_picker)

        recyclerView = findViewById(R.id.type_recyclerview)
        val layoutMgr = GridLayoutManager(this,4)
        recyclerView.layoutManager = layoutMgr
        var adapter :TypeAdapter = if (intent.getBooleanExtra("isCost",false)){
            TypeAdapter(Constants.costTypeList,this)
        } else{
            TypeAdapter(Constants.incomeTypeList,this)
        }
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        setResult(RESULT_CANCELED)
    }
}