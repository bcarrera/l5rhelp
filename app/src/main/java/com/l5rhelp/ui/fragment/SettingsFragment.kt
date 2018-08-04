package com.l5rhelp.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import com.l5rhelp.R
import com.l5rhelp.dagger.submodules.SettingsModule
import com.l5rhelp.ui.activity.MainActivity
import com.l5rhelp.ui.presenter.SettingsPresenter
import com.l5rhelp.ui.utils.DataBasePeriodicity
import com.l5rhelp.ui.utils.app
import com.l5rhelp.ui.utils.toast
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject


class SettingsFragment : Fragment(), SettingsPresenter.View {

    //Dagger
    @Inject lateinit var presenter: SettingsPresenter
    val component by lazy { activity?.app?.component?.plus(SettingsModule(this)) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        component?.inject(this)

        init() 
    }

    private fun init () {

        settings_load_photo_checkbox.isChecked = presenter.loadPhoto()!!
        settings_load_photo_checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            presenter.setLoadPhoto(isChecked)
        }

        settings_upgrade_cards_DB_button.setOnClickListener {
            presenter.updateCards()
        }

        settings_upgrade_rules_DB_button.setOnClickListener {
            presenter.updateRules()
        }

    }

    override fun showLoading() {
        (activity as MainActivity).showLoading()
    }

    override fun hideLoading() {
        (activity as MainActivity).hideLoading()
    }

    override fun upgradingCardsDBSuccess() {
        context?.toast(getString(R.string.settings_upgrade_cards_DB_success))
    }

    override fun upgradingDBError() {
        context?.toast(getString(R.string.settings_upgrade_DB_error))
    }

    override fun upgradingRulesDBSuccess() {
        context?.toast(getString(R.string.settings_upgrade_rules_DB_success))
    }
}
