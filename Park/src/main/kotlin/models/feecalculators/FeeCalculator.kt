package models.feecalculators

import models.VehicleType
import models.feemodels.FeeModel

interface FeeCalculator {
    fun getFinalPrice(duration: Long, vehicleType: VehicleType): Long
}

