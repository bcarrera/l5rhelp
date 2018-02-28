package com.l5rhelp.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.l5rhelp.R
import com.l5rhelp.domain.model.Card
import com.l5rhelp.ui.utils.Utils
import com.l5rhelp.ui.utils.hide
import com.l5rhelp.ui.utils.loadUrl
import com.l5rhelp.ui.utils.show
import kotlinx.android.synthetic.main.fragment_card_detail.*


class CardDetailFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(Utils.hasNetworkConnection(context)) {
            initImage(arguments?.getSerializable("card") as Card)
        } else {
            initTextInfo(arguments?.getSerializable("card") as Card)
        }
    }

    fun initImage (card : Card) {
        card_detail_text_info_layout.hide()
        card_detail_image.show()
        if(card.packCards[0].imageUrl.isNullOrEmpty()) {
            card_detail_image.loadUrl(card.packCards[1].imageUrl)
        } else {
            card_detail_image.loadUrl(card.packCards[0].imageUrl)
        }
    }

    fun initTextInfo (card : Card) {
        card_detail_image.hide()
        card_detail_text_info_layout.show()
        when(card.clan) {
            "scorpion" -> {
                card_detail_mon_image.setBackgroundResource(R.drawable.ic_mon_scorpion)}
            "dragon" -> {
                card_detail_mon_image.setBackgroundResource(R.drawable.ic_mon_dragon)}
            "lion" -> {
                card_detail_mon_image.setBackgroundResource(R.drawable.ic_mon_lion)}
            "crane" -> {
                card_detail_mon_image.setBackgroundResource(R.drawable.ic_mon_crane)}
            "crab" ->{
                card_detail_mon_image.setBackgroundResource(R.drawable.ic_mon_crab)}
            "phoenix" ->{
                card_detail_mon_image.setBackgroundResource(R.drawable.ic_mon_phoenix)}
            "unicorn" -> {
                card_detail_mon_image.setBackgroundResource(R.drawable.ic_mon_unicorn)}
            "neutral" ->{
                card_detail_mon_image.setBackgroundResource(R.drawable.ic_mon_neutral)}
        }

        card_detail_name.text = card.name.capitalize()
        card_detail_clan.text = card.clan.capitalize()
        card_detail_type.text = "${card.type.capitalize()}."

        val stringBuffer = StringBuffer()
        for (item in card.traits) {
            stringBuffer.append(item.capitalize()).append(".")
        }
        card_detail_traits.text = stringBuffer.toString()
        card_detail_cost.text = "Cost: ${card.cost.toString()}."

        if(card.military != null ){
            card_detail_militar.text = "Militar: ${card.military}."
        } else if (card.militaryBonus != null){
            card_detail_militar.text = "Militar: ${card.militaryBonus}."
        }

        if(card.political != null){
            card_detail_political.text = "Political: ${card.political}."
        } else if (card.politicalBonus != null){
            card_detail_political.text = "Political: ${card.politicalBonus}."
        }

        if(card.glory != null) card_detail_glory?.text = "Glory: ${card.glory}."

        card_detail_text.text = card.textCanonical?.capitalize()

        if(card.packCards[0].flavor != null){
            card_detail_flavor.show()
            card_detail_flavor.text = card.packCards[0].flavor
        } else {
            card_detail_flavor.hide()
        }

        card_detail_pack.text = card.packCards[0].pack.id.capitalize()
        card_detail_ilustrator.text = " - " + card.packCards[0].illustrator

    }

}// Required empty public constructor
