package models.feecalculators

import models.VehicleType
import models.feemodels.FeeModel

abstract class FeeCalculator {
    abstract fun getFinalPrice(duration: Long, vehicleType: VehicleType): Long

    protected fun calculatePrice(feeModel: FeeModel, duration: Long, rate: Long): Long {
        return feeModel.calculateFee(duration, rate)
    }
}

