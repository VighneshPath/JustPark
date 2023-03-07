package models.feecalculators

import models.VehicleType
import models.VehicleType.CAR
import models.feemodels.FeeModel

abstract class FeeCalculator {
    abstract fun getFinalPrice(duration: Long, vehicleType: VehicleType = CAR): Long

    fun calculatePrice(feeModel: FeeModel, duration: Long, rate: Long): Long {
        return feeModel.calculateFee(duration, rate)
    }
}

