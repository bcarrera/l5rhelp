package com.l5rhelp.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.l5rhelp.R
import com.l5rhelp.dagger.submodules.RulesModule
import com.l5rhelp.domain.model.Ruling
import com.l5rhelp.ui.activity.MainActivity
import com.l5rhelp.ui.adapter.RulesAdapter
import com.l5rhelp.ui.presenter.RulesPresenter
import com.l5rhelp.ui.utils.app
import com.l5rhelp.ui.utils.hideKeyboard
import com.l5rhelp.ui.utils.replaceFragmentSafely
import kotlinx.android.synthetic.main.fragment_rules.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class RulesFragment : Fragment(), RulesPresenter.View {

    //Dagger
    @Inject lateinit var mPresenter: RulesPresenter
    val component by lazy { activity?.app?.component?.plus(RulesModule(this)) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rules, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        component?.inject(this)

        init()
        mPresenter.initPresenter()
    }

    private fun init () {
        rules_search_imageview?.setOnClickListener {
            mPresenter.filterByName(rules_search_edittext?.text.toString())
            rules_search_imageview.hideKeyboard()
        }

        rules_search_edittext.setOnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mPresenter.filterByName(rules_search_edittext?.text.toString())
                rules_search_edittext.hideKeyboard()
                handled = true
            }
            handled
        }
    }

//    fun init () {
//        rules_search_webview.settings.javaScriptEnabled = true
//        rules_search_webview.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//                view?.loadUrl("https://fiveringsdb.com/rules/reference/")
//                return true
//            }
//        }
//        rules_search_webview.loadUrl("https://fiveringsdb.com/rules/reference/")
//    }

    override fun showLoading() {
        (activity as MainActivity).showLoading()
    }

    override fun hideLoading() {
        (activity as MainActivity).hideLoading()
    }

    override fun initPresenterSuccess(rulesList: List<Ruling>) {
        populatRecycler(rulesList)
    }

    private fun populatRecycler(rulesList: List<Ruling>) {
        rules_search_recycler?.layoutManager = LinearLayoutManager(context)
        val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        rules_search_recycler?.addItemDecoration(itemDecor)
        rules_search_recycler?.adapter = RulesAdapter(rulesList) {

        }
    }

    override fun filterSuccess(rulingList: List<Ruling>) {
        populatRecycler(rulingList)
    }


}
