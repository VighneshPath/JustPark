package main.models

import java.time.LocalDateTime

data class Receipt(val receiptNumber: Long, val spotNumber: Long, val entryDateTime: LocalDateTime, val feePerHour: Long, val exitDateTime: LocalDateTime=LocalDateTime.now())
