package com.ptw.fantasyleagueapp.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.ptw.fantasyleagueapp.R
import com.ptw.fantasyleagueapp.adapter.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var indicators: Array<ImageView>? = null
    private var page: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mSectionsPagerAdapter = SectionsPagerAdapter(getSupportFragmentManager());
        indicators = arrayOf(intro_indicator_0, intro_indicator_1, intro_indicator_2, intro_indicator_3)
        mViewPager.adapter = mSectionsPagerAdapter
        mViewPager.currentItem = page
        updateIndicators(page)
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
            override fun onPageSelected(position: Int) {
                page = position;
                updateIndicators(page);
                when (position) {
                    0 ->
                        mViewPager.currentItem
                    1 ->
                        mViewPager.currentItem
                    2 ->
                        mViewPager.currentItem
                    3 ->
                        mViewPager.currentItem
                }
                intro_btn_next.visibility = if (position == 3) View.GONE else View.VISIBLE
                intro_btn_finish.visibility = if (position == 3) View.VISIBLE else View.GONE
            }
        })
        intro_btn_next.setOnClickListener {
            page += 1
            mViewPager.setCurrentItem(page, true)
        }
        intro_btn_skip.setOnClickListener { finish() }
        intro_btn_finish.setOnClickListener {
            finish()
        }
    }

    private fun updateIndicators(position: Int) {
        for (i in indicators!!.indices) {
            indicators?.get(i)?.setBackgroundResource(
                if (i == position) R.drawable.indicator_selected else R.drawable.indicator_unselected
            )
        }
    }


}

