package models.feecalculators

import models.VehicleType
import models.feedata.FeeData
import models.feemodels.FeeModel

interface FeeCalculator{
    val feeData: FeeData
    fun getFinalPrice(duration: Long, vehicleType: VehicleType): Long
}

