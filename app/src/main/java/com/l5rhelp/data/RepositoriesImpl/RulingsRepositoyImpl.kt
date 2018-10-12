package com.l5rhelp.data.RepositoriesImpl

import com.l5rhelp.domain.model.Ruling
import com.l5rhelp.domain.repositories.RulingsRepository

class RulingsRepositoyImpl : RulingsRepository {
    override fun getAllRulings(): List<Ruling>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRulingByCardId(code: String): Ruling {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRulingDetail(card_id: String, ruling_id: String): Ruling {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}