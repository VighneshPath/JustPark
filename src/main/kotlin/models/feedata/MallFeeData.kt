package models.feedata

import models.VehicleType
import models.feecalculators.Interval

class MallFeeData: FeeData {
    override fun getIntervals(vehicleType: VehicleType): List<Interval> {
        return listOf(Interval(0, Long.MAX_VALUE))
    }

    override fun getRates(vehicleType: VehicleType): List<Long> {
        return when (vehicleType) {
            VehicleType.CAR -> listOf(20L)
            VehicleType.TWO_WHEELER -> listOf(10L)
            VehicleType.HEAVY_VEHICLE -> listOf(50L)
        }
    }
}