package models.feecalculators

import models.VehicleType
import models.feemodels.DailyFeeModel
import models.feemodels.FeeModel
import models.feemodels.FlatFeeModel
import java.lang.Long.min

class AirportFeeCalculator : FeeCalculator {
    override fun getFinalPrice(duration: Long, vehicleType: VehicleType): Long {
        val intervals = getIntervals(vehicleType)
        val rates = getRates(vehicleType)
        var feeModel: FeeModel = FlatFeeModel()

        var finalPrice = 0L
        intervals.forEachIndexed { index, it ->
            if (index == intervals.size - 1) {
                feeModel = DailyFeeModel()
            }
            if (duration >= it.start) {
                val minDuration = min(duration, it.end)
                finalPrice = feeModel.calculateFee(minDuration - it.start, rates[index])
            }
        }

        return finalPrice
    }

    private fun getIntervals(vehicleType: VehicleType): List<Interval> {
        return when (vehicleType) {
            VehicleType.TWO_WHEELER -> listOf(
                Interval(0L, 1L),
                Interval(1L, 8L),
                Interval(8L, 24L),
                Interval(24L, Long.MAX_VALUE)
            )

            VehicleType.CAR -> listOf(Interval(0L, 12L), Interval(12L, 24L), Interval(24L, Long.MAX_VALUE))
            VehicleType.HEAVY_VEHICLE -> listOf(Interval(0L, 0L))
        }
    }

    private fun getRates(vehicleType: VehicleType): List<Long> {
        return when (vehicleType) {
            VehicleType.TWO_WHEELER -> listOf(0L, 40L, 60L, 80L)
            VehicleType.CAR -> listOf(60L, 80L, 100L)
            VehicleType.HEAVY_VEHICLE -> listOf(0L)
        }
    }
}