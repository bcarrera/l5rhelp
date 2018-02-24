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


class CardsFragment : Fragment(), CardsPresenter.View {

    //Dagger
    @Inject lateinit var mPresenter: CardsPresenter
    val component by lazy { activity?.app?.component?.plus(CardsModule(this)) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_cards, container, false)

        component?.inject(this)

        val cardsSearchImage = v?.findViewById<ImageView>(R.id.cards_search_imageview)
        val cardsSearchEditText = v?.findViewById<EditText>(R.id.cards_search_edittext)

        cardsSearchImage?.setOnClickListener { mPresenter.filterByName(cardsSearchEditText?.text.toString())  }

        //init()

        return v
    }

    private fun init () {
        val cardsSearchImage = view?.findViewById<ImageView>(R.id.cards_search_imageview)
        val cardsSearchEditText = view?.findViewById<EditText>(R.id.cards_search_edittext)

        cardsSearchImage?.setOnClickListener { mPresenter.filterByName(cardsSearchEditText?.text.toString())  }
    }

    override fun filterByNameSuccess(cardList: List<Card>) {
        val cardsRecycler = view?.findViewById<RecyclerView>(R.id.cards_search_recycler)
        cardsRecycler?.layoutManager = LinearLayoutManager(context)
        cardsRecycler?.adapter = CardsAdapter(cardList) {
            context?.toast("${it.name} Clicked")
        }

    }


}
