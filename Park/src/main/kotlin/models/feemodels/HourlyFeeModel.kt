package models.feemodels

class HourlyFeeModel : FeeModel {
    override fun calculateFee(duration: Long, fare: Long): Long {
        if (duration < 0) return 0
        if (duration == 0L) return fare
        return duration * fare
    }
}
