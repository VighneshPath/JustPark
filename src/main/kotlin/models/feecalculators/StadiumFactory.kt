package models.feecalculators

import models.VehicleType
import models.VehicleType.*

object StadiumFactory {
    fun getIntervals(vehicleType: VehicleType): List<Interval> {
        return when (vehicleType) {
            TWO_WHEELER -> listOf(Interval(0L, 4L), Interval(4L, 12L), Interval(12L, Long.MAX_VALUE))
            CAR -> listOf(Interval(0L, 4L), Interval(4L, 12L), Interval(12L, Long.MAX_VALUE))
            HEAVY_VEHICLE -> listOf(Interval(0L, 0L))
        }
    }

    fun getRates(vehicleType: VehicleType): List<Long> {
        return when (vehicleType) {
            TWO_WHEELER -> listOf(30L, 60L, 100L)
            CAR -> listOf(60L, 120L, 200L)
            HEAVY_VEHICLE -> listOf(0L)
        }
    }
}