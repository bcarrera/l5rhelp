package com.l5rhelp.base.net

import com.l5rhelp.domain.model.CardsResponse
import com.l5rhelp.domain.model.RulingsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FiveRingsDBService {

    companion object {
        const val ROOT_URL = "https://api.fiveringsdb.com"
    }

    //Cards
    @GET("/cards")
    fun getAllCards(): Call<CardsResponse>

    @GET("/cards/{code}")
    fun getCardByCode(@Path(value="code") code: String): Call<CardsResponse>

    //Rulings
    @GET("/rulings")
    fun getAllRulings(): Call<RulingsResponse>

    @GET("/cards/{card_id}/rulings")
    fun getRulingByCardId(@Path(value="card_id") card_id: String): Call<RulingsResponse>

    @GET("/cards/{card_id}/rulings/{ruling_id}")
    fun getRulingDetail(@Path(value="card_id") card_id: String, @Path(value="ruling_id") ruling_id: String): Call<RulingsResponse>

}