package models.feecalculators

import models.VehicleType
import models.feemodels.FeeModel
import models.feemodels.HourlyFeeModel

class MallFeeCalculator : FeeCalculator {
    override fun getFinalPrice(duration: Long, vehicleType: VehicleType): Long {
        val feePerHour = getRate(vehicleType)
        val feeModel : FeeModel = HourlyFeeModel()
        return feeModel.calculateFee(duration, feePerHour)
    }

    private fun getRate(vehicleType: VehicleType): Long {
        return when (vehicleType) {
            VehicleType.CAR -> 20L
            VehicleType.TWO_WHEELER -> 10L
            VehicleType.HEAVY_VEHICLE -> 50L
        }
    }
}