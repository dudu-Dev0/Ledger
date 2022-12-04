package com.dudu.ledger.ui.activities

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.dudu.ledger.R
import com.dudu.ledger.adapters.AnalysisFragmentPagerAdapter
import com.dudu.ledger.ui.fragments.AnalysisCostFragment
import com.dudu.ledger.ui.fragments.AnalysisIncomeFragment


class CostAnalysis : AppCompatActivity(), ViewPager.OnPageChangeListener {
    lateinit var analysisViewPager:ViewPager
    lateinit var circle1:ImageView
    lateinit var circle2:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cost_analysis)

        analysisViewPager = findViewById(R.id.vp_analysis)
        circle1 = findViewById(R.id.circle_1)
        circle2 = findViewById(R.id.circle_2)

        val viewList = ArrayList<Fragment>()
        viewList.add(AnalysisCostFragment())
        viewList.add(AnalysisIncomeFragment())
        val fragmentPagerAdapter = AnalysisFragmentPagerAdapter(supportFragmentManager, viewList)
        analysisViewPager.setAdapter(fragmentPagerAdapter)
        analysisViewPager.setCurrentItem(0)
        analysisViewPager.addOnPageChangeListener(this)

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
    override fun onPageSelected(position: Int) {}
    override fun onPageScrollStateChanged(state: Int) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            when (analysisViewPager.getCurrentItem()) {
                0 -> {
                    circle1.background = getDrawable(R.drawable.circle_chosen)
                    circle2.background = getDrawable(R.drawable.circle)
                }
                1 -> {
                    circle1.background = getDrawable(R.drawable.circle)
                    circle2.background = getDrawable(R.drawable.circle_chosen)
                }
            }
        }
    }
}

