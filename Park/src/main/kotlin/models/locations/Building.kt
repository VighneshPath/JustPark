package models.locations

import models.*
import models.vehicles.Car
import models.vehicles.Vehicle
import java.time.LocalDateTime

class Building(val ticketBooth: TicketBooth, val receiptBooth: ReceiptBooth, private val floorSizes: List<Long>) {
    private var floors: MutableMap<Long, Floor> = mutableMapOf()

    init {
        floors[0] = Floor(0L, 0L)
        for(index in 1..floorSizes.size){
            floors[index.toLong()] = Floor(index.toLong(), floorSizes[index-1])
        }
    }

    private fun getNextAvailableFloor(): Floor? {
        return floors[1]
    }

    fun parkVehicle(vehicle: Vehicle, entryTime: LocalDateTime): Ticket? {
        val floor = getNextAvailableFloor()
        if (floor != null) {
            val spot = floor.getNextAvailableSpot()
            if(spot != null){
                floor.setSpotTo(spot.getSpotsNumber(), vehicle)
                val ticket = ticketBooth.getTicket(spot.getSpotsNumber(),
                    floor.getFloorNumber(),
                    entryTime
                )
                vehicle.setTicketTo(ticket)
                return ticket
            }
        }
        return null
    }
}
