package models.feemodels

class FlatFeeModel: FeeModel{
    override fun calculateFee(duration: Long, fare: Long): Long {
        return fare
    }
}