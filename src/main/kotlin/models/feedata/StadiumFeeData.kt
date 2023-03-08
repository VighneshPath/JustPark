package models.feedata

import models.VehicleType
import models.feecalculators.Interval

class StadiumFeeData: FeeData {
    override fun getIntervals(vehicleType: VehicleType): List<Interval> {
        return when (vehicleType) {
            VehicleType.TWO_WHEELER -> listOf(Interval(0L, 4L), Interval(4L, 12L), Interval(12L, Long.MAX_VALUE))
            VehicleType.CAR -> listOf(Interval(0L, 4L), Interval(4L, 12L), Interval(12L, Long.MAX_VALUE))
            VehicleType.HEAVY_VEHICLE -> listOf(Interval(0L, 0L))
        }
    }

    override fun getRates(vehicleType: VehicleType): List<Long> {
        return when (vehicleType) {
            VehicleType.TWO_WHEELER -> listOf(30L, 60L, 100L)
            VehicleType.CAR -> listOf(60L, 120L, 200L)
            VehicleType.HEAVY_VEHICLE -> listOf(0L)
        }
    }

}