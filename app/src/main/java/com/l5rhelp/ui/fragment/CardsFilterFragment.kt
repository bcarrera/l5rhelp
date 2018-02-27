package com.l5rhelp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.l5rhelp.R
import com.l5rhelp.ui.utils.CardsSearchFilters
import com.l5rhelp.ui.utils.FiltersDialog
import com.l5rhelp.ui.utils.replaceFragmentSafely

import kotlinx.android.synthetic.main.activity_cards_filter.*

class CardsFilterFragment : Fragment(), FiltersDialog.Listener {

    var clanFiltersList : List<String> = emptyList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.activity_cards_filter, container, false)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    fun init () {

        cards_filters_clan_layout.setOnClickListener {
            val clanFilterDialog = FiltersDialog(context, CardsSearchFilters.CLAN)
            clanFilterDialog.setListener(this)
            clanFilterDialog.show()
        }

        cards_filters_button.setOnClickListener {
            val cardsFragment = CardsFragment()
            cardsFragment.setFilters(clanFiltersList)
            activity?.replaceFragmentSafely(cardsFragment, "CardsFragment", false, R.id.main_content)
        }

    }

    override fun filtersDone(filtersList: List<String>, filterType: CardsSearchFilters) {
        when(filterType) {
            CardsSearchFilters.CLAN -> {
                val  clansBuffer  = StringBuffer()
                for (item in filtersList){
                    clansBuffer.append(item.capitalize()).append(" ")
                }
                cards_filters_clan_selection.text = clansBuffer.toString()
                clanFiltersList = filtersList
            }
            CardsSearchFilters.TYPE -> TODO()
            CardsSearchFilters.DECK -> TODO()
            CardsSearchFilters.PACK -> TODO()
        }
    }

}
