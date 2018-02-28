package com.l5rhelp.domain.interactors

import com.l5rhelp.base.net.FiveRingsDBService
import com.l5rhelp.domain.model.CardsResponse
import com.l5rhelp.domain.model.RulingsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Krupto on 22/02/2018.
 */

class GetAllRulingsInteractor(private val fiveRingsDBService : FiveRingsDBService) {

    fun getAllRulingsInteractor (mPresenter : GetAllRulingsInteractor.Presenter) {

        val call = fiveRingsDBService.getAllRulings()

        call.enqueue(object : Callback<RulingsResponse> {
            override fun onResponse(call: Call<RulingsResponse>?, response: Response<RulingsResponse>?) {
                if (response != null && response.isSuccessful) {
                    mPresenter.getAllRulingsSuccess(response.body())
                } else {
                    mPresenter.getAllRulingsError()
                }
            }

            override fun onFailure(call: Call<RulingsResponse>?, t: Throwable?) {
                mPresenter.getAllRulingsError()
            }
        })
    }

    interface Presenter {
        fun getAllRulingsSuccess(response : RulingsResponse?)
        fun getAllRulingsError()
    }

}