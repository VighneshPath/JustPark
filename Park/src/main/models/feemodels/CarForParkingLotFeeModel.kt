package main.models.feemodels

class CarForParkingLotFeeModel: FeeModel {
    override fun getRate(): Long {
        return 10
    }
}