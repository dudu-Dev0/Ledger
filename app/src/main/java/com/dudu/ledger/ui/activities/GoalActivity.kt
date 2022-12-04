package com.dudu.ledger.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dudu.ledger.R
import com.dudu.ledger.bean.Danmaku
import com.dudu.ledger.ui.widgets.WaveProgress
import com.orient.tea.barragephoto.adapter.BarrageAdapter
import com.orient.tea.barragephoto.adapter.BarrageAdapter.BarrageViewHolder
import com.orient.tea.barragephoto.ui.BarrageView
import java.util.*


class GoalActivity : AppCompatActivity() {
    private lateinit var goalText: TextView
    private lateinit var progress: WaveProgress
    private lateinit var targetAmountTextView: TextView
    private lateinit var nowAmountTextView: TextView
    private lateinit var completeGoal: Button
    private lateinit var saveMoney: Button
    private lateinit var danmaku :BarrageView
    private lateinit var mAdapter: BarrageAdapter<Danmaku>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)

        goalText = findViewById(R.id.my_goal_text)
        progress = findViewById(R.id.wave_progress)
        targetAmountTextView = findViewById(R.id.target_amount_text)
        nowAmountTextView = findViewById(R.id.existing_amount)
        completeGoal = findViewById(R.id.complete_the_goal)
        saveMoney = findViewById(R.id.bt_save_money)
        danmaku = findViewById(R.id.danmaku)

        initDanmaku()

        initData()
    }
    fun initData(){
        val prefs = getSharedPreferences("goal", MODE_PRIVATE)
        val goal = prefs.getString("goal", "")
        val target = prefs.getFloat("target_goal_amount", 0F)
        val now = prefs.getFloat("now_amount", 0F)

        goalText.text = goal
        targetAmountTextView.text = target.toString()
        nowAmountTextView.text = now.toString()

        progress.value = now / target * 100
        Log.e("Goal",progress.value.toString())
        saveMoney.setOnClickListener {
            val intent =Intent(this,NumericKeyboardActivity::class.java)
            startActivityForResult(intent,114514)
        }
        if (now/target*100 >= 100) {
            completeGoal.visibility = View.VISIBLE
            saveMoney.visibility = View.INVISIBLE
            completeGoal.setOnClickListener {
                with(getSharedPreferences("goal", MODE_PRIVATE).edit()) {
                    putString("goal", null)
                    putFloat("target_goal_amount", 0f)
                    putFloat("now_amount", now - target)
                    putBoolean("is_existing", false) //true表示计划存在，false则反之
                    apply()
                }
                Toast.makeText(this, "恭喜，您已完成目标，快去炫耀一下吧！", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
    fun initDanmaku(){
        val options = BarrageView.Options()
            .setGravity(BarrageView.GRAVITY_MIDDLE)                // 设置弹幕的位置
            .setInterval(1400)                                     // 设置弹幕的发送间隔
            .setSpeed(100,10)                   // 设置速度和波动值
            .setModel(BarrageView.MODEL_RANDOM)     // 设置弹幕生成模式
            .setRepeat(1)                                       // 循环播放 默认为1次 -1 为无限循环
            .setClick(false);
        danmaku.setOptions(options)
        mAdapter = object : BarrageAdapter<Danmaku>(null, this) {
            override fun onCreateViewHolder(root: View, type: Int): ViewHolder {
                return ViewHolder(root)
            }

            override fun getItemLayout(barrageData: Danmaku?): Int {
                return R.layout.danmaku_item
            }
        }
        danmaku.setAdapter(mAdapter)
    }
    class ViewHolder(itemView: View) : BarrageViewHolder<Danmaku>(itemView) {
        private val danmakuText: TextView

        init {
            danmakuText = itemView.findViewById(R.id.danmaku_text)
        }

        override fun onBind(data: Danmaku) {
            danmakuText.text = data.text
        }
    }

    private fun initDanmakuData() {
        val dataList = listOf(Danmaku("冰冻三尺非一日之寒",0,0)
            , Danmaku("不积跬步无以至千里",0,1)
            , Danmaku("不积小流无以成江海",0,2)
            , Danmaku("千里之行始于足下",0,3)
            , Danmaku("水滴石穿绳锯木断",0,4)
            , Danmaku("真积力久则入",0,5)
            , Danmaku("驽马十驾功在不舍",0,6)
        )
        Collections.shuffle(dataList)
        mAdapter.addList(dataList)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        initDanmakuData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            114514 -> {
                with(getSharedPreferences("goal", MODE_PRIVATE).edit()) {
                    putFloat("now_amount",getSharedPreferences("goal", MODE_PRIVATE).getFloat("now_amount",0F) + data!!.getStringExtra("keyboard_data")!!.toFloat())
                    apply()
                    initData()
                }
            }
        }
    }

}