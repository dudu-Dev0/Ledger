package com.dudu.ledger

import com.dudu.ledger.bean.Ledger
import com.dudu.ledger.bean.Type


object Constants {
    const val ANTI_ALIAS = true
    const val DEFAULT_SIZE = 150
    const val DEFAULT_START_ANGLE = 270
    const val DEFAULT_SWEEP_ANGLE = 360
    const val DEFAULT_ANIM_TIME = 1000
    const val DEFAULT_MAX_VALUE = 100
    const val DEFAULT_VALUE = 0
    const val DEFAULT_HINT_SIZE = 15
    const val DEFAULT_UNIT_SIZE = 30
    const val DEFAULT_VALUE_SIZE = 15
    const val DEFAULT_ARC_WIDTH = 1
    const val DEFAULT_WAVE_HEIGHT = 40
    val costTypeList = mutableListOf(
        Type("娱乐",R.drawable.amusement),
        Type("教育",R.drawable.education),
        Type("吃喝",R.drawable.food),
        Type("生活缴费",R.drawable.living_payment),
        Type("医疗",R.drawable.medical),
        Type("宠物",R.drawable.pets),
        Type("发红包",R.drawable.redpack),
        Type("运动",R.drawable.sports),
        Type("旅行",R.drawable.travel),
        Type("购物",R.drawable.shopping),
        Type("人情",R.drawable.human_favor))
    val incomeTypeList = mutableListOf(
        Type("零花钱",R.drawable.pocket_money),
        Type("利息",R.drawable.interest),
        Type("工作",R.drawable.work),
        Type("兼职",R.drawable.parttime_job),
        Type("退款",R.drawable.refund),
        Type("收红包",R.drawable.redpack))
}