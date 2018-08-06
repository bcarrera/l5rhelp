package com.l5rhelp.domain.interactors

import com.l5rhelp.base.net.FiveRingsDBService
import com.l5rhelp.domain.model.CardsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Krupto on 22/02/2018.
 */

class GetAllCardsInteractor (private val fiveRingsDBService : FiveRingsDBService) {

    fun getAllCardsInteractor (mPresenter : GetAllCardsInteractor.Presenter) {

        val call = fiveRingsDBService.getAllCards()

        call.enqueue(object : Callback<CardsResponse> {
            override fun onResponse(call: Call<CardsResponse>?, response: Response<CardsResponse>?) {
                if (response != null && response.isSuccessful) {
                    mPresenter.getAllCardsSuccess(response.body())
                } else {
                    mPresenter.getAllCardsError()
                }
            }

            override fun onFailure(call: Call<CardsResponse>?, t: Throwable?) {
                mPresenter.getAllCardsError()
            }
        })
    }

    interface Presenter {
        fun getAllCardsSuccess(response : CardsResponse?)
        fun getAllCardsError()
    }

}