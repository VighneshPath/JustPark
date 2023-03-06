package models

import java.time.LocalDateTime

data class Receipt(
    val receiptNumber: Long,
    val spotNumber: Long,
    val entryDateTime: LocalDateTime,
    val fee: Long,
    val exitDateTime: LocalDateTime,
    val floorNumber: Int = 1
)
