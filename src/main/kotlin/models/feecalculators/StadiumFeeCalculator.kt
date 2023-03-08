package models.feecalculators

import models.VehicleType
import models.feemodels.FeeModel
import models.feemodels.FlatFeeModel
import models.feemodels.HourlyFeeModel
import java.lang.Long.min

class StadiumFeeCalculator : FeeCalculator {
    override fun getFinalPrice(duration: Long, vehicleType: VehicleType): Long {
        val intervals = getIntervals(vehicleType)
        val rates = getRates(vehicleType)
        var feeModel: FeeModel = FlatFeeModel()

        var finalPrice = 0L
        intervals.forEachIndexed { index, it ->
            if (index == intervals.size - 1) {
                feeModel = HourlyFeeModel()
            }
            if (duration >= it.start) {
                val minDuration = min(duration, it.end)
                finalPrice += feeModel.calculateFee(minDuration - it.start, rates[index])
            }
        }

        return finalPrice
    }

    fun getIntervals(vehicleType: VehicleType): List<Interval> {
        return when (vehicleType) {
            VehicleType.TWO_WHEELER -> listOf(Interval(0L, 4L), Interval(4L, 12L), Interval(12L, Long.MAX_VALUE))
            VehicleType.CAR -> listOf(Interval(0L, 4L), Interval(4L, 12L), Interval(12L, Long.MAX_VALUE))
            VehicleType.HEAVY_VEHICLE -> listOf(Interval(0L, 0L))
        }
    }

    fun getRates(vehicleType: VehicleType): List<Long> {
        return when (vehicleType) {
            VehicleType.TWO_WHEELER -> listOf(30L, 60L, 100L)
            VehicleType.CAR -> listOf(60L, 120L, 200L)
            VehicleType.HEAVY_VEHICLE -> listOf(0L)
        }
    }
}