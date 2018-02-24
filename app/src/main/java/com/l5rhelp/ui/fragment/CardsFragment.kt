package com.l5rhelp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView

import com.l5rhelp.R
import com.l5rhelp.dagger.submodules.CardsModule
import com.l5rhelp.domain.model.Card
import com.l5rhelp.ui.adapter.CardsAdapter
import com.l5rhelp.ui.presenter.CardsPresenter
import com.l5rhelp.ui.utils.app
import com.l5rhelp.ui.utils.toast
import javax.inject.Inject
import android.support.v7.widget.DividerItemDecoration
import com.l5rhelp.ui.utils.hide
import com.l5rhelp.ui.utils.show
import kotlinx.android.synthetic.main.fragment_cards.*


class CardsFragment : Fragment(), CardsPresenter.View {

    //Dagger
    @Inject lateinit var mPresenter: CardsPresenter
    val component by lazy { activity?.app?.component?.plus(CardsModule(this)) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_cards, container, false)
        component?.inject(this)

        return v
    }

    override fun onResume() {
        super.onResume()
        init()
    }

    private fun init () {
        cards_search_imageview?.setOnClickListener { mPresenter.filterByName(cards_search_edittext?.text.toString())  }
    }

    override fun filterByNameSuccess(cardList: List<Card>) {
        cards_search_recycler?.show()
        cards_instructions_text.hide()

        cards_search_recycler?.layoutManager = LinearLayoutManager(context)
        val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        cards_search_recycler?.addItemDecoration(itemDecor)
        cards_search_recycler?.adapter = CardsAdapter(cardList) {
            context?.toast("${it.name} Clicked")
        }

    }


}
