package com.l5rhelp.data.sharedPreferences

import android.content.Context
import android.content.SharedPreferences

class GameScores (context: Context) {

    companion object {
        private const val PREFS_NAME = "l5rhelp.gameScores"
        private const val P1_CLAN = "p1_clan"
        private const val P1_HONOR = "p1_honor"
        private const val P1_FATE = "p1_fate"
        private const val P1_DIAL = "p1_dial"
        private const val P2_CLAN = "p2_clan"
        private const val P2_HONOR = "p2_honor"
        private const val P2_FATE = "p2_fate"
        private const val P2_DIAL = "p2_dial"
    }

    private val prefs: SharedPreferences? = context.getSharedPreferences(PREFS_NAME, 0)

    //Player 1

    var player1Clan: Int?
    get() = prefs?.getInt(P1_CLAN, 0)
    set(value) = value?.let { prefs?.edit()?.putInt(P1_CLAN, it)?.apply() }!!

    var player1Honor: String?
        get() = prefs?.getString(P1_HONOR, "0")
        set(value) = value?.let { prefs?.edit()?.putString(P1_HONOR, it)?.apply() }!!

    var player1Fate: String?
        get() = prefs?.getString(P1_FATE, "0")
        set(value) = value?.let { prefs?.edit()?.putString(P1_FATE, it)?.apply() }!!

    var player1Dial: String?
        get() = prefs?.getString(P1_DIAL, "0")
        set(value) = value?.let { prefs?.edit()?.putString(P1_DIAL, it)?.apply() }!!

    //Player 2

    var player2Clan: Int?
        get() = prefs?.getInt(P2_CLAN, 0)
        set(value) = value?.let { prefs?.edit()?.putInt(P2_CLAN, it)?.apply() }!!

    var player2Honor: String?
        get() = prefs?.getString(P2_HONOR, "0")
        set(value) = value?.let { prefs?.edit()?.putString(P2_HONOR, it)?.apply() }!!

    var player2Fate: String?
        get() = prefs?.getString(P2_FATE, "0")
        set(value) = value?.let { prefs?.edit()?.putString(P2_FATE, it)?.apply() }!!

    var player2Dial: String?
        get() = prefs?.getString(P2_DIAL, "0")
        set(value) = value?.let { prefs?.edit()?.putString(P2_DIAL, it)?.apply() }!!
}