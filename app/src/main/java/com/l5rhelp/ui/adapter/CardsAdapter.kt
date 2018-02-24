package com.l5rhelp.ui.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.l5rhelp.R
import com.l5rhelp.domain.model.Card
import com.l5rhelp.ui.utils.inflate
import kotlinx.android.synthetic.main.item_list_card.view.*

/**
 * Created by Krupto on 24/02/2018.
 */
class CardsAdapter(val items: List<Card>, val listener: (Card) -> Unit) : RecyclerView.Adapter<CardsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_list_card))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(item: Card, listener: (Card) -> Unit) = with(itemView) {
            item_card_name.text = item.name.capitalize()

            when(item.clan) {
                "scorpion" -> {
                    item_card_name.setTextColor(ContextCompat.getColor(context, R.color.scorpion_color))
                    item_card_mon.setBackgroundResource(R.drawable.ic_mon_scorpion)}
                "dragon" -> {
                    item_card_name.setTextColor(ContextCompat.getColor(context, R.color.dragon_color))
                    item_card_mon.setBackgroundResource(R.drawable.ic_mon_dragon)}
                "lion" -> {
                    item_card_name.setTextColor(ContextCompat.getColor(context, R.color.lion_color))
                    item_card_mon.setBackgroundResource(R.drawable.ic_mon_lion)}
                "crane" -> {
                    item_card_name.setTextColor(ContextCompat.getColor(context, R.color.crane_color))
                    item_card_mon.setBackgroundResource(R.drawable.ic_mon_crane)}
                "crab" ->{
                    item_card_name.setTextColor(ContextCompat.getColor(context, R.color.crab_color))
                    item_card_mon.setBackgroundResource(R.drawable.ic_mon_crab)}
                "phoenix" ->{
                    item_card_name.setTextColor(ContextCompat.getColor(context, R.color.phoenix_color))
                    item_card_mon.setBackgroundResource(R.drawable.ic_mon_phoenix)}
                "unicorn" -> {
                    item_card_name.setTextColor(ContextCompat.getColor(context, R.color.unicorn_color))
                    item_card_mon.setBackgroundResource(R.drawable.ic_mon_unicorn)}
                "neutral" ->{
                    item_card_name.setTextColor(ContextCompat.getColor(context, R.color.neutral_color))
                    item_card_mon.setBackgroundResource(R.drawable.ic_mon_neutral)}
            }
            item_card_clan.text = item.clan.capitalize()
            item_card_type.text = item.type.capitalize()
            item_card_deck.text = "- ${item.side.capitalize()} Deck"

            setOnClickListener { listener(item) }
        }
    }
}