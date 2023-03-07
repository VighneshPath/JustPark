package models.feemodels

interface FeeModel {
    fun calculateFee(duration: Long, fare: Long): Long
}

