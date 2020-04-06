package com.nexware.coronaTracknew.ui.ui.splash

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nexware.coronaTracknew.R
import com.nexware.coronaTracknew.ui.ui.home.HomeFragment
import com.nexware.coronaTracknew.ui.ui.utlies.events.NextPageEvent
import kotlinx.android.synthetic.main.fragment_start.*
import org.greenrobot.eventbus.EventBus


class StartFragment : Fragment() {

    val MyPREFERENCES = "MyPrefs"
    var pref: SharedPreferences? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
        pref = activity!!.getSharedPreferences(HomeFragment.MyPREFERENCES, Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        startButton.setOnClickListener {
            pref?.edit()?.putBoolean(getString(R.string.toutorial), true)?.apply()
            EventBus.getDefault().post(NextPageEvent())
        }
    }

    companion object {

        fun newInstance(): Fragment {
            return StartFragment()
        }
    }
}
