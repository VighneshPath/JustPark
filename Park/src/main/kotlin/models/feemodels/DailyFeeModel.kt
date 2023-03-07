package models.feemodels

class DailyFeeModel : FeeModel {
    override fun calculateFee(duration: Long, fare: Long): Long {
        if (duration < 0) return 0
        if (duration == 0L) return fare
        return ((duration / 24.0) * fare).toLong()
    }
}
