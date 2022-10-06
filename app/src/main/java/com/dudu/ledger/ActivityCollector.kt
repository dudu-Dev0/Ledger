package com.dudu.ledger

import android.app.Activity

object ActivityCollector {
    private val activities = ArrayList<Activity>()

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }
    fun rmActivity(activity: Activity) {
        activities.remove(activity)
    }
    fun finishAll() {
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        activities.clear()
    }
    fun finishAllWithNoAnim() {
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
                activity.overridePendingTransition(0,0)
            }
        }
        activities.clear()
    }
}