package com.ptw.fantasyleagueapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ptw.fantasyleagueapp.R


class PlaceholderFragment() : Fragment() {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    var img: ImageView? = null
  //  var bgs: IntArray? = intArrayOf(R.drawable.ic_flight_24dp, R.drawable.ic_mail_24dp, R.drawable.ic_explore_24dp)

    companion object {
        val ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            val fragment = PlaceholderFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_pager, container, false)
        val textView = rootView.findViewById<View>(R.id.tvAppScreenHeading) as TextView
       // textView.text = getString(R.string.section_format, arguments!!.getInt(ARG_SECTION_NUMBER))

        img = rootView.findViewById<View>(R.id.ivFantasyImage) as ImageView
       // img!!.setBackgroundResource(bgs!![arguments!!.getInt(ARG_SECTION_NUMBER) - 1])
        return rootView
    }


}