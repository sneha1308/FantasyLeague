package com.ptw.fantasyleagueapp.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.facebook.login.LoginManager
import com.ptw.fantasyleagueapp.R
import com.ptw.fantasyleagueapp.adapter.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_tour.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*


class TourActivity : AppCompatActivity() {
    var indicators: Array<ImageView>? = null
    private var page: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tour)
        setSupportActionBar(includeLayout.toolbar)
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
                if (position == 3) {
                    intro_btn_next.text = ("FINISH")
                } else {
                    intro_btn_next.text = ("Next")
                }
            }
        })
        intro_btn_next.setOnClickListener {
            if (page == 3) {
                finish()
            } else {
                page += 1
                mViewPager.setCurrentItem(page, true)
            }

        }
        intro_btn_skip.setOnClickListener {
            finish()
        }
        /*intro_btn_finish.setOnClickListener {
            finish()
        }*/

    }

    private fun updateIndicators(position: Int) {
        for (i in indicators!!.indices) {
            indicators?.get(i)?.setBackgroundResource(
                if (i == position) R.drawable.indicator_selected else R.drawable.indicator_unselected
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LoginManager.getInstance().logOut();
    }

}

