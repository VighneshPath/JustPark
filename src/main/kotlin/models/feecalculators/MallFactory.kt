package models.feecalculators

import models.VehicleType
import models.VehicleType.*

object MallFactory {
    fun getRate(vehicleType: VehicleType): Long {
        return when (vehicleType) {
            CAR -> 20L
            TWO_WHEELER -> 10L
            HEAVY_VEHICLE -> 50L
        }
    }
}
