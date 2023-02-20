package models.receipts

class NullReceipt: Receipt {
    override fun isNull(): Boolean {
        return true
    }

}