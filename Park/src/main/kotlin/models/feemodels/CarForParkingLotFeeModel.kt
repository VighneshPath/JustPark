package models.feemodels

import constants.CAR_PARKING_LOT_FEE_PER_HOUR
import main.models.feemodels.FeeModel

class CarForParkingLotFeeModel: FeeModel {
    override fun getRate(): Long {
        return CAR_PARKING_LOT_FEE_PER_HOUR
    }
}