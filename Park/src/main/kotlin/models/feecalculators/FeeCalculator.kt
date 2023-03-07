package models.feecalculators

import models.feemodels.FeeModel

abstract class FeeCalculator {
    abstract fun getFinalPrice(duration: Long): Long

    fun calculatePrice(feeModel: FeeModel, duration: Long, rate: Long): Long {
        return feeModel.calculateFee(duration, rate)
    }
}

