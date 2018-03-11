package com.l5rhelp.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter

import com.l5rhelp.R
import com.l5rhelp.dagger.submodules.GameCounterModule
import com.l5rhelp.ui.presenter.GameCounterPresenter
import com.l5rhelp.ui.utils.app
import kotlinx.android.synthetic.main.fragment_game_counter.*
import javax.inject.Inject

class GameCounterFragment : Fragment(), GameCounterPresenter.View {

    var isInitializing1 : Boolean = true
    var isInitializing2 : Boolean = true

    //Dagger
    @Inject
    lateinit var mPresenter: GameCounterPresenter
    val component by lazy { activity?.app?.component?.plus(GameCounterModule(this)) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_counter, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        component?.inject(this)
        init()
    }

    private fun init() {

        var honorCounter = Integer.parseInt(mPresenter.getAllGameScores().player1Honor)
        var fateCounter  = Integer.parseInt(mPresenter.getAllGameScores().player1Fate)
        var dialCounter = Integer.parseInt(mPresenter.getAllGameScores().player1Dial)
        var honorCounter2 = Integer.parseInt(mPresenter.getAllGameScores().player2Honor)
        var fateCounter2  = Integer.parseInt(mPresenter.getAllGameScores().player2Honor)
        var dialCounter2= Integer.parseInt(mPresenter.getAllGameScores().player2Honor)
        val clansAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, context?.resources?.getStringArray(R.array.playing_clans_list))
        clansAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //Player 1
        game_clan_spinner.adapter = clansAdapter
        if (mPresenter.getAllGameScores().player1Clan != -1){
            game_clan_spinner.setSelection(mPresenter.getAllGameScores().player1Clan!!)
        }
        game_clan_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                setClanInfo(selectedItem, 1)
                mPresenter.getAllGameScores().player1Clan = position
                honorCounter = Integer.parseInt(game_honor_counter_1.text.toString())
                fateCounter = Integer.parseInt(game_fate_counter_1.text.toString())
                dialCounter = Integer.parseInt(game_dial_counter_1.text.toString())
                isInitializing1 = false
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        game_honor_add_1.setOnClickListener {
            if (honorCounter in 0..24) {
                honorCounter++
                game_honor_counter_1.text = honorCounter.toString()
                mPresenter.getAllGameScores().player1Honor = honorCounter.toString()
            }
        }
        game_honor_remove_1.setOnClickListener {
            if (honorCounter in 1..25) {
                honorCounter--
                game_honor_counter_1.text = honorCounter.toString()
                mPresenter.getAllGameScores().player1Honor = honorCounter.toString()
            }
        }

        game_fate_add_1.setOnClickListener {
            if (fateCounter >= 0) {
                fateCounter++
                game_fate_counter_1.text = fateCounter.toString()
                mPresenter.getAllGameScores().player1Fate = fateCounter.toString()
            }
        }
        game_fate_remove_1.setOnClickListener {
            if (fateCounter > 0) {
                fateCounter--
                game_fate_counter_1.text = fateCounter.toString()
                mPresenter.getAllGameScores().player1Fate = fateCounter.toString()
            }
        }

        game_dial_add_1.setOnClickListener {
            if (dialCounter in 0..4) {
                dialCounter++
                game_dial_counter_1.text = dialCounter.toString()
                mPresenter.getAllGameScores().player1Dial = dialCounter.toString()
            }
        }
        game_dial_remove_1.setOnClickListener {
            if (dialCounter in 1..5) {
                dialCounter--
                game_dial_counter_1.text = dialCounter.toString()
                mPresenter.getAllGameScores().player1Dial = dialCounter.toString()
            }
        }

        //Player 2
        game_clan_spinner_2.adapter = clansAdapter
        if (mPresenter.getAllGameScores().player2Clan != -1){
            game_clan_spinner_2.setSelection(mPresenter.getAllGameScores().player2Clan!!)
        }
        game_clan_spinner_2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                setClanInfo(selectedItem, 2)
                mPresenter.getAllGameScores().player2Clan = position
                honorCounter2 = Integer.parseInt(game_honor_counter_2.text.toString())
                fateCounter2 = Integer.parseInt(game_fate_counter_2.text.toString())
                dialCounter2 = Integer.parseInt(game_dial_counter_2.text.toString())
                isInitializing2 = false
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        game_honor_add_2.setOnClickListener {
            if (honorCounter2 in 0..24) {
                honorCounter2++
                game_honor_counter_2.text = honorCounter2.toString()
                mPresenter.getAllGameScores().player2Honor = honorCounter2.toString()
            }
        }
        game_honor_remove_2.setOnClickListener {
            if (honorCounter2 in 1..25) {
                honorCounter2--
                game_honor_counter_2.text = honorCounter2.toString()
                mPresenter.getAllGameScores().player2Honor = honorCounter2.toString()
            }
        }

        game_fate_add_2.setOnClickListener {
            if (fateCounter2 >= 0) {
                fateCounter2++
                game_fate_counter_2.text = fateCounter2.toString()
                mPresenter.getAllGameScores().player2Fate = fateCounter2.toString()
            }
        }
        game_fate_remove_2.setOnClickListener {
            if (fateCounter2 > 0) {
                fateCounter2--
                game_fate_counter_2.text = fateCounter2.toString()
                mPresenter.getAllGameScores().player2Fate = fateCounter2.toString()
            }
        }

        game_dial_add_2.setOnClickListener {
            if (dialCounter2 in 0..4) {
                dialCounter2++
                game_dial_counter_2.text = dialCounter2.toString()
                mPresenter.getAllGameScores().player2Dial = dialCounter2.toString()
            }
        }
        game_dial_remove_2.setOnClickListener {
            if (dialCounter2 in 1..5) {
                dialCounter2--
                game_dial_counter_2.text = dialCounter2.toString()
                mPresenter.getAllGameScores().player2Dial = dialCounter2.toString()
            }
        }

    }

    private fun setClanInfo(clan: String, player: Int) {
        when (clan) {
            "scorpion" -> {
                if (player == 1) {
                    game_clan_image.setBackgroundResource(R.drawable.ic_mon_scorpion)
                    setInitCounters("10", "7", 1)
                } else {
                    game_clan_image_2.setBackgroundResource(R.drawable.ic_mon_scorpion)
                    setInitCounters("10", "7", 2)
                }
            }

            "dragon" -> {
                if (player == 1) {
                    game_clan_image.setBackgroundResource(R.drawable.ic_mon_dragon)
                    setInitCounters("11", "7", 1)

                } else {
                    game_clan_image_2.setBackgroundResource(R.drawable.ic_mon_dragon)
                    setInitCounters("11", "7", 2)
                }
            }
            "lion" -> {
                if (player == 1) {
                    game_clan_image.setBackgroundResource(R.drawable.ic_mon_lion)
                    setInitCounters("12", "7", 1)
                } else {
                    game_clan_image_2.setBackgroundResource(R.drawable.ic_mon_lion)
                    setInitCounters("12", "7", 2)
                }
            }
            "crane" -> {
                if (player == 1) {
                    game_clan_image.setBackgroundResource(R.drawable.ic_mon_crane)
                    setInitCounters("11", "7", 1)

                } else {
                    game_clan_image_2.setBackgroundResource(R.drawable.ic_mon_crane)
                    setInitCounters("11", "7", 2)
                }
            }
            "crab" -> {
                if (player == 1) {
                    game_clan_image.setBackgroundResource(R.drawable.ic_mon_crab)
                    setInitCounters("10", "7", 1)
                } else {
                    game_clan_image_2.setBackgroundResource(R.drawable.ic_mon_crab)
                    setInitCounters("10", "7", 2)
                }
            }
            "phoenix" -> {
                if (player == 1) {
                    game_clan_image.setBackgroundResource(R.drawable.ic_mon_phoenix)
                    setInitCounters("11", "7", 1)
                } else {
                    game_clan_image_2.setBackgroundResource(R.drawable.ic_mon_phoenix)
                    setInitCounters("11", "7", 2)
                }
            }
            "unicorn" -> {
                if (player == 1) {
                    game_clan_image.setBackgroundResource(R.drawable.ic_mon_unicorn)
                    setInitCounters("10", "7", 1)
                } else {
                    game_clan_image_2.setBackgroundResource(R.drawable.ic_mon_unicorn)
                    setInitCounters("10", "7", 2)
                }
            }
        }
    }

    private fun setInitCounters(honor: String, fate: String, player: Int) {
        if (player == 1) {
            if(isInitializing1 && mPresenter.getAllGameScores().player1Clan != -1){
                game_honor_counter_1.text = mPresenter.getAllGameScores().player1Honor
                game_fate_counter_1.text = mPresenter.getAllGameScores().player1Fate
                game_dial_counter_1.text = mPresenter.getAllGameScores().player1Dial
            } else {
                game_honor_counter_1.text = honor
                game_fate_counter_1.text = fate
                game_dial_counter_1.text = "0"
            }
        } else {
            if(isInitializing2 && mPresenter.getAllGameScores().player2Clan != -1){
                game_honor_counter_2.text = mPresenter.getAllGameScores().player2Honor
                game_fate_counter_2.text = mPresenter.getAllGameScores().player2Fate
                game_dial_counter_2.text = mPresenter.getAllGameScores().player2Dial
            } else {
                game_honor_counter_2.text = honor
                game_fate_counter_2.text = fate
                game_dial_counter_2.text = "0"
            }
        }
    }
}
