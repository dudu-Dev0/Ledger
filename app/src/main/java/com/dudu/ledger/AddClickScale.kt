package com.dudu.ledger

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View

class AddClickScale {
    /**
     * 添加点击缩放效果
     */
    companion object {
        @SuppressLint("ClickableViewAccessibility")
        fun addClickScale(view: View, scale: Float = 0.9f, duration: Long = 150) {
            view.setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        view.animate().scaleX(scale).scaleY(scale).setDuration(duration).start()
                    }

                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        view.animate().scaleX(1f).scaleY(1f).setDuration(duration).start()
                    }
                }
                // 点击事件处理，交给View自身
                view.onTouchEvent(event)
            }
        }
    }

}