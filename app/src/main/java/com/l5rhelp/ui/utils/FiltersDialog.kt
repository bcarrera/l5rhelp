package com.l5rhelp.ui.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.l5rhelp.R
import kotlinx.android.synthetic.main.filters_dialog.*


class FiltersDialog(context: Context?, val type : CardsSearchFilters) : Dialog(context) {

    private lateinit var mListener : Listener
    var selectedFiltersList : MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filters_dialog)

        when(type){
            CardsSearchFilters.CLAN -> {
                cards_filters_type_checkboxes.hide()
                cards_filters_deck_checkboxes.hide()
                cards_filters_clans_checkboxes.show()
            }
            CardsSearchFilters.TYPE -> {
                cards_filters_deck_checkboxes.hide()
                cards_filters_clans_checkboxes.hide()
                cards_filters_type_checkboxes.show()
            }
            CardsSearchFilters.DECK -> {
                cards_filters_type_checkboxes.hide()
                cards_filters_clans_checkboxes.hide()
                cards_filters_deck_checkboxes.show()
            }
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
                CardsSearchFilters.TYPE -> {
                    if(type_attachment_checkbox.isChecked) selectedFiltersList.add(type_attachment_checkbox.text.toString().toLowerCase())
                    if(type_character_checkbox.isChecked) selectedFiltersList.add(type_character_checkbox.text.toString().toLowerCase())
                    if(type_event_checkbox.isChecked) selectedFiltersList.add(type_event_checkbox.text.toString().toLowerCase())
                    if(type_holding_checkbox.isChecked) selectedFiltersList.add(type_holding_checkbox.text.toString().toLowerCase())
                    if(type_province_checkbox.isChecked) selectedFiltersList.add(type_province_checkbox.text.toString().toLowerCase())
                    if(type_role_checkbox.isChecked) selectedFiltersList.add(type_role_checkbox.text.toString().toLowerCase())
                    if(type_stronghold_checkbox.isChecked) selectedFiltersList.add(type_stronghold_checkbox.text.toString().toLowerCase())

                    mListener.filtersDone(selectedFiltersList, CardsSearchFilters.TYPE)
                    dismiss()
                }
                CardsSearchFilters.DECK -> {
                    if(deck_conflict_checkbox.isChecked) selectedFiltersList.add(deck_conflict_checkbox.text.toString().toLowerCase())
                    if(deck_dinasty_checkbox.isChecked) selectedFiltersList.add(deck_dinasty_checkbox.text.toString().toLowerCase())

                    mListener.filtersDone(selectedFiltersList, CardsSearchFilters.DECK)
                    dismiss()
                }
                CardsSearchFilters.PACK -> TODO()
            }
        }
    }

    fun setListener(listener: Listener) {
        mListener = listener
    }

    interface Listener {
        fun filtersDone(filtersList : MutableList<String>, filterType : CardsSearchFilters)
    }
}