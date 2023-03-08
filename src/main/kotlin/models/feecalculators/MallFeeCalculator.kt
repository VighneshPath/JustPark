package models.feecalculators

import models.VehicleType
import models.feedata.FeeData
import models.feemodels.FeeModel
import models.feemodels.HourlyFeeModel

class MallFeeCalculator(override val feeData: FeeData) : FeeCalculator {
    override fun getFinalPrice(duration: Long, vehicleType: VehicleType): Long {
        val rates = feeData.getRates(vehicleType)
        val intervals = feeData.getIntervals(vehicleType)
        val feeModel : FeeModel = HourlyFeeModel()

        var finalPrice = 0L

        intervals.forEachIndexed { index, it ->
            if (duration >= it.start) {
                val minDuration = java.lang.Long.min(duration, it.end)
                finalPrice = feeModel.calculateFee(minDuration - it.start, rates[index])
            }
        }

        return feeModel.calculateFee(duration, finalPrice)
    }
}