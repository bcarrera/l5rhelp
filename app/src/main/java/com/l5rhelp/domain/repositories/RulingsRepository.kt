package com.l5rhelp.domain.repositories

import com.l5rhelp.domain.model.Ruling

interface RulingsRepository {
    fun getAllRulings(): List<Ruling>?
    fun getRulingByCardId(code: String): Ruling
    fun getRulingDetail(card_id: String, ruling_id: String): Ruling
}