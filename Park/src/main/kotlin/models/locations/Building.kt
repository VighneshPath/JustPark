package models.locations

import models.Floor
import models.ReceiptBooth
import models.Spot
import models.TicketBooth

class Building(val ticketBooth: TicketBooth, val receiptBooth: ReceiptBooth, private val floorSizes: List<Long>) {
    private var floors: MutableMap<Long, Floor> = mutableMapOf()

    init {
        floors[0] = Floor(0L, 0L)
        for(index in 1..floorSizes.size){
            floors[index.toLong()] = Floor(index.toLong(), floorSizes[index-1])
        }
    }

    fun getNextAvailableFloor(): Floor? {
        return floors[1]
    }
}
