package models.receipts

import java.time.LocalDateTime

data class NormalReceipt(
    val receiptNumber: Long,
    val floorNumber: Long,
    val spotNumber: Long,
    val entryDateTime: LocalDateTime,
    val fee: Long,
    val exitDateTime: LocalDateTime
) : Receipt {
    override fun isNull(): Boolean {
        return false
    }
}