package main.models

import java.time.LocalDateTime

data class Receipt(val receiptNumber: Long, val spotNumber: Long, val entryDateTime: LocalDateTime, val exitDateTime: LocalDateTime=LocalDateTime.now())
