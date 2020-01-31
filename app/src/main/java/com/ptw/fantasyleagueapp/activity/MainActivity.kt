package com.ptw.fantasyleagueapp.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.ptw.fantasyleagueapp.R
import com.ptw.fantasyleagueapp.adapter.SectionsPagerAdapter


class MainActivity : AppCompatActivity() {


    var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    var indicators: Array<ImageView>? = null
    private var page: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        }

        setContentView(R.layout.activity_main)

        val mSectionsPagerAdapter = SectionsPagerAdapter(getSupportFragmentManager());

        val mNextBtn = findViewById<TextView>(R.id.intro_btn_next);
        val mSkipBtn = findViewById<TextView>(R.id.intro_btn_skip);
        val mFinishBtn = findViewById<TextView>(R.id.intro_btn_finish);

        val zero = findViewById<ImageView>(R.id.intro_indicator_0);
        val one = findViewById<ImageView>(R.id.intro_indicator_1);
        val two = findViewById<ImageView>(R.id.intro_indicator_2);
        val three = findViewById<ImageView>(R.id.intro_indicator_3);

        indicators = arrayOf(zero, one, two, three)

        // Set up the ViewPager with the sections adapter.
        // Set up the ViewPager with the sections adapter.
        val mViewPager = findViewById<ViewPager>(R.id.container)
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
                mNextBtn.visibility = if (position == 3) View.GONE else View.VISIBLE
                mFinishBtn.visibility = if (position == 3) View.VISIBLE else View.GONE

                // val  mCoordinator = findViewById<ConstraintLayout>(R.id.main_content);
                /*if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP)
                    mNextBtn.setImageDrawable(
                        Utils.tintMyDrawable(ContextCompat.getDrawable(this, R.drawable.ic_chevron_right_24dp), Color.WHITE)
                    );*/


            }


        })


        mNextBtn.setOnClickListener {
            page += 1
            mViewPager.setCurrentItem(page, true)
        }

        mSkipBtn.setOnClickListener { finish() }

        mFinishBtn.setOnClickListener {
            finish()
            //  update 1st time pref
          //  Utils.saveSharedSetting(this@PagerActivity, MainActivity.PREF_USER_FIRST_TIME, "false")
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

