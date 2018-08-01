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

    private var clanFiltersList : MutableList<String> = mutableListOf()
    private var typeFiltersList : MutableList<String> = mutableListOf()
    private var deckFiltersList : MutableList<String> = mutableListOf()
    private var cost : MutableList<String> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.activity_cards_filter, container, false)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()
    }

    private fun init () {
        clanFiltersList = context?.resources?.getStringArray(R.array.clan_filter)!!.toMutableList()
        typeFiltersList = context?.resources?.getStringArray(R.array.type_filter)!!.toMutableList()
        deckFiltersList = context?.resources?.getStringArray(R.array.deck_filter)!!.toMutableList()

        cards_filters_clan_layout.setOnClickListener {
            val clanFilterDialog = FiltersDialog(context, CardsSearchFilters.CLAN)
            clanFilterDialog.setListener(this)
            clanFilterDialog.show()
        }

        cards_filters_type_layout.setOnClickListener {
            val clanFilterDialog = FiltersDialog(context, CardsSearchFilters.TYPE)
            clanFilterDialog.setListener(this)
            clanFilterDialog.show()
        }

        cards_filters_deck_layout.setOnClickListener {
            val clanFilterDialog = FiltersDialog(context, CardsSearchFilters.DECK)
            clanFilterDialog.setListener(this)
            clanFilterDialog.show()
        }

        cards_filters_cost_layout.setOnClickListener {
            val clanFilterDialog = FiltersDialog(context, CardsSearchFilters.COST)
            clanFilterDialog.setListener(this)
            clanFilterDialog.show()
        }

        cards_filters_button.setOnClickListener {
            val cardsFragment = CardsFragment()
            cardsFragment.setFilters(clanFiltersList, typeFiltersList, deckFiltersList, cost)
            activity?.replaceFragmentSafely(cardsFragment, "CardsFragment", false, R.id.main_content)
        }

    }

    override fun filtersDone(filtersList: MutableList<String>, filterType: CardsSearchFilters) {
        when(filterType) {
            CardsSearchFilters.CLAN -> {
                val  clansBuffer  = StringBuffer()
                for (item in filtersList){
                    clansBuffer.append(item.capitalize()).append(" ")
                }
                cards_filters_clan_selection.text = clansBuffer.toString()
                clanFiltersList.clear()
                clanFiltersList = filtersList
            }
            CardsSearchFilters.TYPE ->{
                val  clansBuffer  = StringBuffer()
                for (item in filtersList){
                    clansBuffer.append(item.capitalize()).append(" ")
                }
                cards_filters_type_selection.text = clansBuffer.toString()
                typeFiltersList.clear()
                typeFiltersList = filtersList
            }

            CardsSearchFilters.DECK ->{
            val  clansBuffer  = StringBuffer()
            for (item in filtersList){
                clansBuffer.append(item.capitalize()).append(" ")
            }
            cards_filters_deck_selection.text = clansBuffer.toString()
            deckFiltersList.clear()
            deckFiltersList = filtersList
        }

            CardsSearchFilters.COST ->{
                cards_filters_cost_selection.text = filtersList[0] + " -> " + filtersList[1]
                cost.clear()
                cost = filtersList
            }

            CardsSearchFilters.PACK -> TODO()
        }
    }

}
