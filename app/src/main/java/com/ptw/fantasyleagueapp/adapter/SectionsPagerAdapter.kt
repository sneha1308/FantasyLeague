package com.ptw.fantasyleagueapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ptw.fantasyleagueapp.fragment.PlaceholderFragment

class SectionsPagerAdapter(private val fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return PlaceholderFragment.newInstance(position + 1)
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            1 -> return "Section 1"
            2 -> return "Section 2"
            3 -> return "Section 3"
            4 -> return "Section 4"
        }
        return null
    }
}