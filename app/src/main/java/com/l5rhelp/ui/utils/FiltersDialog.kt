package com.l5rhelp.ui.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import com.appyvet.materialrangebar.RangeBar
import com.l5rhelp.R
import kotlinx.android.synthetic.main.filters_dialog.*


class FiltersDialog(context: Context?, val type : CardsSearchFilters) : Dialog(context), RangeBar.OnRangeBarChangeListener {

    private lateinit var mListener : Listener
    var selectedFiltersList : MutableList<String> = mutableListOf()
    var traitsBuilder = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filters_dialog)
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)

        when(type){
            CardsSearchFilters.CLAN -> {
                cards_filters_title.text = context.getString(R.string.cards_filters_clan_title)
                cards_filters_type_checkboxes.hide()
                cards_filters_deck_checkboxes.hide()
                cards_filters_clans_checkboxes.show()
                cards_filters_range_bar_layout.hide()
                cards_filter_traits_layout.hide()

                cards_filters_select_all_checkbox.setOnClickListener{
                    if (!cards_filters_select_all_checkbox.isChecked) {
                        clan_crab_checkbox.isChecked = false
                        clan_crane_checkbox.isChecked = false
                        clan_dragon_checkbox.isChecked = false
                        clan_lion_checkbob.isChecked = false
                        clan_neutral_checkbox.isChecked = false
                        clan_phoenix_checkbox.isChecked = false
                        clan_scorpion_checkbox.isChecked = false
                        clan_unicorn_checkbox.isChecked = false
                    } else {
                        clan_crab_checkbox.isChecked  = true
                        clan_crane_checkbox.isChecked  = true
                        clan_dragon_checkbox.isChecked  = true
                        clan_lion_checkbob.isChecked  = true
                        clan_neutral_checkbox.isChecked  = true
                        clan_phoenix_checkbox.isChecked  = true
                        clan_scorpion_checkbox.isChecked  = true
                        clan_unicorn_checkbox.isChecked  = true
                    }
                }
            }
            CardsSearchFilters.TYPE -> {
                cards_filters_title.text = context.getString(R.string.cards_filters_type_title)
                cards_filters_deck_checkboxes.hide()
                cards_filters_clans_checkboxes.hide()
                cards_filters_type_checkboxes.show()
                cards_filters_range_bar_layout.hide()
                cards_filter_traits_layout.hide()

                cards_filters_select_all_checkbox.setOnClickListener{
                    if (!cards_filters_select_all_checkbox.isChecked) {
                        type_attachment_checkbox.isChecked = false
                        type_character_checkbox.isChecked = false
                        type_event_checkbox.isChecked = false
                        type_holding_checkbox.isChecked = false
                        type_province_checkbox.isChecked = false
                        type_role_checkbox.isChecked = false
                        type_stronghold_checkbox.isChecked = false
                    } else {
                        type_attachment_checkbox.isChecked  = true
                        type_character_checkbox.isChecked  = true
                        type_event_checkbox.isChecked  = true
                        type_holding_checkbox.isChecked  = true
                        type_province_checkbox.isChecked  = true
                        type_role_checkbox.isChecked  = true
                        type_stronghold_checkbox.isChecked  = true
                    }
                }
            }
            CardsSearchFilters.DECK -> {
                cards_filters_title.text = context.getString(R.string.cards_filters_deck_title)
                cards_filters_type_checkboxes.hide()
                cards_filters_clans_checkboxes.hide()
                cards_filters_deck_checkboxes.show()
                cards_filters_range_bar_layout.hide()
                cards_filter_traits_layout.hide()

                cards_filters_select_all_checkbox.setOnClickListener{
                    if (!cards_filters_select_all_checkbox.isChecked) {
                        deck_conflict_checkbox.isChecked = false
                        deck_dinasty_checkbox.isChecked = false
                    } else {
                        deck_conflict_checkbox.isChecked  = true
                        deck_dinasty_checkbox.isChecked  = true
                    }
                }
            }

            CardsSearchFilters.COST -> {
                cards_filters_title.text = context.getString(R.string.cards_filters_cost_title)
                cards_filters_type_checkboxes.hide()
                cards_filters_deck_checkboxes.hide()
                cards_filters_clans_checkboxes.hide()
                cards_filters_clans_checkboxes.hide()
                cards_filters_select_all_checkbox.hide()
                cards_filters_range_bar_layout.show()
                cards_filter_traits_layout.hide()

                cards_filters_range_bar.setOnRangeBarChangeListener(this)
            }

            CardsSearchFilters.TRAITS -> {
                cards_filters_title.text = context.getString(R.string.cards_filters_traits_title)
                cards_filters_type_checkboxes.hide()
                cards_filters_deck_checkboxes.hide()
                cards_filters_clans_checkboxes.hide()
                cards_filters_clans_checkboxes.hide()
                cards_filters_select_all_checkbox.hide()
                cards_filters_range_bar_layout.hide()
                cards_filter_traits_layout.show()
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

                CardsSearchFilters.COST -> {
                    selectedFiltersList.add(cards_filters_range_bar_left_text.text.toString())
                    selectedFiltersList.add(cards_filters_range_bar_right_text.text.toString())

                    mListener.filtersDone(selectedFiltersList, CardsSearchFilters.COST)
                    dismiss()
                }

                CardsSearchFilters.TRAITS -> {
                    mListener.filtersDone(selectedFiltersList, CardsSearchFilters.TRAITS)
                    dismiss()
                }

                CardsSearchFilters.PACK -> TODO()
            }
        }

        cards_filters_traits_imageview.setOnClickListener{
            selectedFiltersList.add(cards_filter_traits_edtitext.text.toString().toLowerCase())
            selectedFiltersList.forEach {
                if(traitsBuilder.isEmpty()) {
                    traitsBuilder.append(cards_filter_traits_edtitext.text.toString())
                } else {
                    traitsBuilder.append(". ").append(cards_filter_traits_edtitext.text.toString())
                }
                cards_filter_traits_edtitext.setText("")
                cards_filter_traits_search.text = traitsBuilder.toString()
            }
        }
    }

    fun setListener(listener: Listener) {
        mListener = listener
    }

    interface Listener {
        fun filtersDone(filtersList : MutableList<String>, filterType : CardsSearchFilters)
    }

    //Seekbar listener

    override fun onRangeChangeListener(rangeBar: RangeBar?, leftPinIndex: Int, rightPinIndex: Int, leftPinValue: String?, rightPinValue: String?) {
        cards_filters_range_bar_left_text.text = leftPinValue.toString()
        cards_filters_range_bar_right_text.text = rightPinValue.toString()
    }


}