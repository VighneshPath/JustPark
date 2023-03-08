package models.feedata

import models.VehicleType
import models.feecalculators.Interval

class AirportFeeData: FeeData {
    override fun getIntervals(vehicleType: VehicleType): List<Interval> {
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

    override fun getRates(vehicleType: VehicleType): List<Long> {
        return when (vehicleType) {
            VehicleType.TWO_WHEELER -> listOf(0L, 40L, 60L, 80L)
            VehicleType.CAR -> listOf(60L, 80L, 100L)
            VehicleType.HEAVY_VEHICLE -> listOf(0L)
        }
    }
}