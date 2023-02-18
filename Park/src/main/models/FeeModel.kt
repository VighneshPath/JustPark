package main.models

interface FeeModel {
    fun getRate(): Long
}

class CarForParkingLotFeeModel: FeeModel{
    override fun getRate(): Long {
        return 10
    }

}