package com.dudu.ledger.bean

import org.litepal.crud.LitePalSupport

class Ledger : LitePalSupport() {
    var year //年份
            = 0
    var month //月份
            = 0
    var day //日
            = 0
    var hour //时
            = 0
    var minute //分
            = 0
    var amount //金额
            = 0.0
    var isCost //收入/支出  收入为true支出为false
            = false
    var type //类型
            : String? = null
    var remark //备注
            : String? = null
}