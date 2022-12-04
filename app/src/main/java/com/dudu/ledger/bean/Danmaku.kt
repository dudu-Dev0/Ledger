package com.dudu.ledger.bean

import com.orient.tea.barragephoto.model.DataSource

data class Danmaku(var text :String, private var type: Int,var pos:Int) : DataSource {
    override fun getType(): Int {
        return this.type
    }
    fun setType(type: Int) {
        this.type = type
    }
}
