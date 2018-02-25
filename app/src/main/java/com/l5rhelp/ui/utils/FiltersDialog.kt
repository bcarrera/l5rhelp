package com.l5rhelp.ui.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.l5rhelp.R
import kotlinx.android.synthetic.main.filters_dialog.*


class FiltersDialog(context: Context?) : Dialog(context) {

    private lateinit var mListener : Listener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filters_dialog)

        cards_filters_button.setOnClickListener{
            mListener.filtersDone(
                    cards_filters_clan_spinner.selectedItem as String,
                    cards_filters_type_spinner.selectedItem as String,
                    cards_filters_deck_spinner.selectedItem as String)
            dismiss()
        }
    }

    fun setListener(listener: Listener) {
        mListener = listener
    }

    interface Listener {
        fun filtersDone(clan : String, type : String, deck : String)
    }
}