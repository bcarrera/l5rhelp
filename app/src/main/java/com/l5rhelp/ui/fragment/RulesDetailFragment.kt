package com.l5rhelp.ui.fragment


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.l5rhelp.R
import com.l5rhelp.domain.model.Ruling
import kotlinx.android.synthetic.main.fragment_rule_detail.*


class RulesDetailFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rule_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initTextInfo(arguments?.getSerializable("ruling") as Ruling)

    }


    fun initTextInfo (ruling : Ruling) {
        rule_detail_name.text = ruling.cardId.cardId.capitalize().replace("-", " ")
        rule_detail_text.text = ruling.text.capitalize()
        rule_detail_source.text = "Source:  ${ruling.source.capitalize()}"
        rule_detail_link.text = ruling.link
        rule_detail_link.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val url = rule_detail_link.text.toString()
            intent.data = Uri.parse(url)
            if (url.startsWith("http://") || url.startsWith("https://")){
                startActivity(intent)
            }
        }

    }

}// Required empty public constructor
