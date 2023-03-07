package models.feecalculators

import models.VehicleType
import models.feemodels.HourlyFeeModel

class MallFeeCalculator : FeeCalculator() {
    override fun getFinalPrice(duration: Long, vehicleType: VehicleType): Long {
        val feePerHour = MallFactory.getRate(vehicleType)
        return super.calculatePrice(HourlyFeeModel(), duration, feePerHour)
    }
}