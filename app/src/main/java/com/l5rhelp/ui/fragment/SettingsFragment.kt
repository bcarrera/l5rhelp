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

        when(presenter.getDataBasePeriodicity()){
            DataBasePeriodicity.MONTHLY.name -> settings_upgrade_DB_spinner.setSelection(0)
            DataBasePeriodicity.WEEKLY.name -> settings_upgrade_DB_spinner.setSelection(1)
            DataBasePeriodicity.NEVER.name -> settings_upgrade_DB_spinner.setSelection(2)
        }

        settings_upgrade_DB_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                when(pos){
                    0 -> presenter.setDataBasePeriodicity(DataBasePeriodicity.MONTHLY.name)
                    1 -> presenter.setDataBasePeriodicity(DataBasePeriodicity.WEEKLY.name)
                    2 -> presenter.setDataBasePeriodicity(DataBasePeriodicity.NEVER.name)
                }
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {

            }
        }
    }

    override fun showLoading() {
        (activity as MainActivity).showLoading()
    }

    override fun hideLoading() {
        (activity as MainActivity).hideLoading()
    }

    override fun upgradingDBSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun upgradingDBError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
