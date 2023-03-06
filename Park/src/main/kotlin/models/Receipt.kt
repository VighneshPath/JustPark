package models

import java.time.LocalDateTime

data class Receipt(
    private val receiptNumber: Long,
    private val spotNumber: Long,
    private val entryDateTime: LocalDateTime,
    private val fee: Long,
    private val exitDateTime: LocalDateTime,
    private val floorNumber: Int = 1
)
