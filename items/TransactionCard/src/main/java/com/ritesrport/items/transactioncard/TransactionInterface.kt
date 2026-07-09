package com.ritesrport.items.transactioncard

interface TransactionInterface {

    fun onTransactionCardClick(transactionId: Long)
}

val transactionInterfacePreview = object : TransactionInterface {
    override fun onTransactionCardClick(transactionId: Long) {}
}