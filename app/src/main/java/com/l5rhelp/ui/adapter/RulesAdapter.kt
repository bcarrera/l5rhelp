package com.l5rhelp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.l5rhelp.R
import com.l5rhelp.domain.model.Ruling
import com.l5rhelp.ui.utils.inflate
import kotlinx.android.synthetic.main.item_list_ruling.view.*

/**
 * Created by Krupto on 24/02/2018.
 */
class RulesAdapter(val items: List<Ruling>, val listener: (Ruling) -> Unit) : RecyclerView.Adapter<RulesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_list_ruling))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(item: Ruling, listener: (Ruling) -> Unit) = with(itemView) {
            item_ruling_name.text = item.cardId.cardId.capitalize()
            item_ruling_source.text = "Source:  ${item.source.capitalize()}"

            setOnClickListener { listener(item) }
        }
    }
}