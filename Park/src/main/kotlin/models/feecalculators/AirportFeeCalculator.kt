package models.feecalculators

import models.VehicleType
import models.feemodels.DailyFeeModel
import models.feemodels.FeeModel
import models.feemodels.FlatFeeModel
import models.feemodels.HourlyFeeModel
import java.lang.Long.min

class AirportFeeCalculator: FeeCalculator(){
    override fun getFinalPrice(duration: Long, vehicleType: VehicleType): Long {
        val intervals = AirportFactory.getIntervals(vehicleType)
        val rates = AirportFactory.getRates(vehicleType)
        var feeModel: FeeModel = FlatFeeModel()

        var finalPrice = 0L
        intervals.forEachIndexed{ index, it->
            if(index == intervals.size-1){
                feeModel = DailyFeeModel()
            }
            if(duration >= it.start){
                val minDuration = min(duration, it.end)
                finalPrice = super.calculatePrice(feeModel, minDuration-it.start, rates[index])
            }
        }

        return finalPrice
    }
}