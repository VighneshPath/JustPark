package models.feemodels

import constants.CAR_PARKING_LOT_FEE_PER_HOUR

class CarForParkingLotFeeModel : FeeModel {
    override fun getRate(): Long {
        return CAR_PARKING_LOT_FEE_PER_HOUR
    }
}