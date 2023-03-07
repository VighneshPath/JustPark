package models.feecalculators

import models.VehicleType
import models.feemodels.DailyFeeModel
import models.feemodels.FeeModel
import models.feemodels.FlatFeeModel
import models.feemodels.HourlyFeeModel

class AirportFeeCalculator: FeeCalculator(){
    override fun getFinalPrice(duration: Long, vehicleType: VehicleType): Long {
        val intervals = StadiumFactory.getIntervals(vehicleType)
        val rates = StadiumFactory.getRates(vehicleType)
        var feeModel: FeeModel = FlatFeeModel()

        var finalPrice = 0L
        intervals.forEachIndexed{ index, it->
            if(index == intervals.size-1){
                feeModel = DailyFeeModel()
            }
            if(duration < it.end){
                finalPrice = super.calculatePrice(feeModel, duration-it.start, rates[index])
            }
        }

        return finalPrice
    }
}