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
import com.l5rhelp.dagger.submodules.CardsModule
import com.l5rhelp.domain.model.Card
import com.l5rhelp.ui.activity.MainActivity
import com.l5rhelp.ui.adapter.CardsAdapter
import com.l5rhelp.ui.presenter.CardsPresenter
import com.l5rhelp.ui.utils.*
import kotlinx.android.synthetic.main.fragment_cards.*
import javax.inject.Inject


class CardsFragment : Fragment(), CardsPresenter.View {

    private var clanFiltersList : List<String> = emptyList()
    private var typeFiltersList : List<String> = emptyList()
    private var deckFiltersList : List<String> = emptyList()
    var cost : Int = 20

    //Dagger
    @Inject lateinit var mPresenter: CardsPresenter
    val component by lazy { activity?.app?.component?.plus(CardsModule(this)) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_cards, container, false)
        component?.inject(this)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        if(clanFiltersList.isNotEmpty() || typeFiltersList.isNotEmpty() || deckFiltersList.isNotEmpty()) mPresenter.useFilters(clanFiltersList, typeFiltersList, deckFiltersList, cost)
    }

    private fun init () {
        cards_search_imageview?.setOnClickListener {
            mPresenter.filterByName(cards_search_edittext?.text.toString())
            cards_search_imageview.hideKeyboard()
        }

        cards_search_edittext.setOnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mPresenter.filterByName(cards_search_edittext?.text.toString())
                cards_search_imageview.hideKeyboard()
                handled = true
            }
            handled
        }

        cards_filters_imageview?.setOnClickListener {
            activity?.addFragment(CardsFilterFragment(), "CardsFilterFragment", R.id.main_content)
        }
    }

    override fun filterSuccess(cardList: List<Card>) {
        if(cardList.isNotEmpty()){
            cards_search_recycler?.show()
            cards_instructions_text.hide()

            cards_search_recycler?.layoutManager = LinearLayoutManager(context)
            val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            cards_search_recycler?.addItemDecoration(itemDecor)
            cards_search_recycler?.adapter = CardsAdapter(cardList) {
                val cardDetailFragment = CardDetailFragment()
                val bundle  = Bundle()
                bundle.putSerializable("card", it)
                cardDetailFragment.arguments = bundle
                activity?.addFragment(cardDetailFragment, cardDetailFragment.javaClass.name, R.id.main_content)
            }
        } else {
            cards_search_recycler?.hide()
            cards_instructions_text.text = getString(R.string.cards_search_no_results)
            cards_instructions_text.show()
        }
    }

    fun setFilters (clanFiltersList: List<String>, typeFiltersList: List<String>, deckFiltersList: List<String>, cost:Int) {
        this.clanFiltersList = clanFiltersList
        this.typeFiltersList = typeFiltersList
        this.deckFiltersList = deckFiltersList
        this.cost = cost
    }

    override fun showLoading() {
        (activity as MainActivity).showLoading()
    }

    override fun hideLoading() {
        (activity as MainActivity).hideLoading()
    }

}
