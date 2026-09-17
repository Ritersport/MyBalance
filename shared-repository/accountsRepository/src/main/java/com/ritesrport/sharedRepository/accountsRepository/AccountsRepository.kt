package com.ritesrport.sharedRepository.accountsRepository

import com.ritesrport.core.database.data.dao.AccountDao
import javax.inject.Inject

class AccountsRepository @Inject constructor(
    private val accountDao: AccountDao
) {
    // В будущем здесь будет маппинг из Entity в DTO
    // Пока оставим заглушки, так как нам важна структура
}
