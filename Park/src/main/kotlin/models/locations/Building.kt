package models.locations

import exceptions.FloorDoesNotExistException
import exceptions.TicketDoesNotExistException
import models.*
import models.tickets.NullTicket
import models.tickets.Ticket
import models.vehicles.Vehicle
import java.time.LocalDateTime

class Building(private val ticketBooth: TicketBooth, private val receiptBooth: ReceiptBooth, floorSizes: List<Long>): Location {
    private var floors: MutableMap<Long, Floor> = mutableMapOf()

    init {
        floors[0] = Floor(0L, 0L)
        for(index in 1..floorSizes.size){
            floors[index.toLong()] = Floor(index.toLong(), floorSizes[index-1])
        }
    }

    private fun getNextAvailableFloor(): Floor? {
        for(index in 1L until floors.size){
            if(!floors[index]!!.isFull()) return floors[index]
        }

        return null
    }

    private fun parkVehicleAndGetTicket(
        floor: Floor,
        spot: Spot,
        vehicle: Vehicle,
        entryTime: LocalDateTime
    ): Ticket {
        floor.setSpotTo(spot.getSpotsNumber(), vehicle)
        val ticket = ticketBooth.getTicket(
            floor.getFloorNumber(),
            spot.getSpotsNumber(),
            entryTime
        )
        vehicle.setTicketTo(ticket)
        return ticket
    }

    override fun parkVehicle(vehicle: Vehicle, entryTime: LocalDateTime): Ticket {
        val floor = getNextAvailableFloor()
        if (floor != null) {
            val spot = floor.getNextAvailableSpot()
            if(spot != null){
                return parkVehicleAndGetTicket(floor, spot, vehicle, entryTime)
            }
        }
        return NullTicket()
    }

    override fun unparkVehicle(vehicle: Vehicle, exitTime: LocalDateTime): Receipt? {
        val ticket = vehicle.getVehicleTicket() ?: throw TicketDoesNotExistException()
        val floor = floors[ticket.getFloorNumberForTicket()]?: throw FloorDoesNotExistException()
        if (floor.clearSpot(ticket.getSpotNumberForTicket())) {
            vehicle.clearTicket()
            return receiptBooth.getReceipt(ticket, exitTime)
        }
        return null
    }
}
