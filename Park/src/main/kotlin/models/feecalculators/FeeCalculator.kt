package models.feecalculators
interface FeeCalculator {
    fun calculateFee(duration: Long, fare: Long): Long
}

