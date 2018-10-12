package com.l5rhelp.domain.repositories

import com.l5rhelp.domain.model.Card

interface CardsRepository {
    fun getAllCards(): List<Card>?
    fun getCardByCode(code: String): Card
}