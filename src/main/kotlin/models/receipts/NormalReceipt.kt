package models.receipts

import java.time.LocalDateTime

data class NormalReceipt(
    private val receiptNumber: Long,
    private val floorNumber: Int,
    private val spotNumber: Int,
    private val entryDateTime: LocalDateTime,
    private val fee: Long,
    private val exitDateTime: LocalDateTime
) : Receipt {
    override fun isNull(): Boolean {
        return false
    }
}