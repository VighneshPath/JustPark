package models.feecalculators

import models.VehicleType
import models.feemodels.FeeModel
import models.feemodels.HourlyFeeModel

class MallFeeCalculator : FeeCalculator {
    override fun getFinalPrice(duration: Long, vehicleType: VehicleType): Long {
        val feePerHour = MallFactory.getRate(vehicleType)
        val feeModel : FeeModel = HourlyFeeModel()
        return feeModel.calculateFee(duration, feePerHour)
    }
}