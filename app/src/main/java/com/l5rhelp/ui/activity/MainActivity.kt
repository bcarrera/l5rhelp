package com.l5rhelp.ui.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.l5rhelp.R
import com.l5rhelp.dagger.submodules.MainModule
import com.l5rhelp.ui.fragment.CardsFragment
import com.l5rhelp.ui.fragment.RulesFragment
import com.l5rhelp.ui.fragment.SettingsFragment
import com.l5rhelp.ui.presenter.MainPresenter
import com.l5rhelp.ui.utils.addFragment
import com.l5rhelp.ui.utils.app
import com.l5rhelp.ui.utils.replaceFragmentSafely
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainPresenter.View {


    //Dagger
    @Inject lateinit var mPresenter: MainPresenter
    val component by lazy { app.component.plus(MainModule(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(this)

        //mPresenter.initPresenter()
        init()
    }

    private fun init () {

        toolbar_navigation_image.setOnClickListener { drawer_layout.openDrawer(GravityCompat.START) }
        nav_view.setNavigationItemSelectedListener(this)

        addFragment(CardsFragment(), "CardFragment", R.id.main_content)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_search -> {
                toolbar_navigation_title.text = getString(R.string.left_menu_option1)
                replaceFragmentSafely(CardsFragment(), "CardsFragment", false, R.id.main_content)
            }
            R.id.nav_rules -> {
                toolbar_navigation_title.text = getString(R.string.left_menu_option2)
                replaceFragmentSafely(RulesFragment(), "RulesFragment", false, R.id.main_content)
            }
            R.id.nav_settings -> {
                toolbar_navigation_title.text = getString(R.string.left_menu_option3)
                replaceFragmentSafely(SettingsFragment(), "SettingsFragment", false, R.id.main_content)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    //Presenter implementations
    override fun initPresenterSuccess() {
        init()
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
