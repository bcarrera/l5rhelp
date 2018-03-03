package com.l5rhelp.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.l5rhelp.R
import com.l5rhelp.data.SharedPreferences
import com.l5rhelp.ui.utils.app
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment() {

    //Dagger
    @Inject lateinit var preferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.app?.component?.inject(this)

        init() 
    }

    fun init () {

        settings_load_photo_checkbox.isChecked = preferences.loadPhoto!!
        settings_load_photo_checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            preferences.loadPhoto = isChecked
        }
    }

}
