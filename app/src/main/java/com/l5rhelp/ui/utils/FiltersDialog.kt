package com.l5rhelp.ui.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.l5rhelp.R
import com.l5rhelp.ui.fragment.CardsFragment
import kotlinx.android.synthetic.main.activity_cards_filter.*
import kotlinx.android.synthetic.main.filters_dialog.*


class FiltersDialog(context: Context?, val type : CardsSearchFilters) : Dialog(context) {

    private lateinit var mListener : Listener
    var selectedFiltersList : MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filters_dialog)

        when(type){
            CardsSearchFilters.CLAN -> {
                cards_filters_clans_layout.show()
            }
            CardsSearchFilters.TYPE -> TODO()
            CardsSearchFilters.DECK -> TODO()
            CardsSearchFilters.PACK -> TODO()
        }

        cards_filters_selection_button.setOnClickListener {
            when(type){
                CardsSearchFilters.CLAN -> {
                    if(clan_crab_checkbox.isChecked) selectedFiltersList.add(clan_crab_checkbox.text.toString().toLowerCase())
                    if(clan_crane_checkbox.isChecked) selectedFiltersList.add(clan_crane_checkbox.text.toString().toLowerCase())
                    if(clan_dragon_checkbox.isChecked) selectedFiltersList.add(clan_dragon_checkbox.text.toString().toLowerCase())
                    if(clan_lion_checkbob.isChecked) selectedFiltersList.add(clan_lion_checkbob.text.toString().toLowerCase())
                    if(clan_neutral_checkbox.isChecked) selectedFiltersList.add(clan_neutral_checkbox.text.toString().toLowerCase())
                    if(clan_phoenix_checkbox.isChecked) selectedFiltersList.add(clan_phoenix_checkbox.text.toString().toLowerCase())
                    if(clan_scorpion_checkbox.isChecked) selectedFiltersList.add(clan_scorpion_checkbox.text.toString().toLowerCase())
                    if(clan_unicorn_checkbox.isChecked) selectedFiltersList.add(clan_unicorn_checkbox.text.toString().toLowerCase())

                    mListener.filtersDone(selectedFiltersList, CardsSearchFilters.CLAN)
                    dismiss()
                }
                CardsSearchFilters.TYPE -> TODO()
                CardsSearchFilters.DECK -> TODO()
                CardsSearchFilters.PACK -> TODO()
            }
        }
    }

    fun setListener(listener: Listener) {
        mListener = listener
    }

    interface Listener {
        fun filtersDone(filtersList : List<String>, filterType : CardsSearchFilters)
    }
}