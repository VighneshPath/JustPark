package models.feedata

import models.VehicleType
import models.feecalculators.Interval

interface FeeData {
    fun getIntervals(vehicleType: VehicleType): List<Interval>
    fun getRates(vehicleType: VehicleType): List<Long>
}