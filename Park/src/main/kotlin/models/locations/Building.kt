package models.locations

import exceptions.FloorDoesNotExistException
import models.Floor
import models.ReceiptBooth
import models.Spot
import models.TicketBooth
import models.receipts.NullReceipt
import models.receipts.Receipt
import models.tickets.NullTicket
import models.tickets.Ticket
import models.vehicles.Vehicle
import java.time.LocalDateTime

class Building(private val ticketBooth: TicketBooth, private val receiptBooth: ReceiptBooth, floorSizes: List<Int>) :
    Location {
    private var floors: MutableList<Floor> = mutableListOf()

    init {
        floors.add(Floor(0, 0))
        for (index in 1..floorSizes.size) {
            floors.add(Floor(index, floorSizes[index - 1]))
        }
    }

    private fun getNextAvailableFloor(): Floor? {
        for (index in 1 until floors.size) {
            if (!floors[index].isFull()) return floors[index]
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
        val floor = getNextAvailableFloor() ?: return NullTicket()
        val spot = floor.getNextAvailableSpot() ?: return NullTicket()

        return parkVehicleAndGetTicket(floor, spot, vehicle, entryTime)
    }

    override fun unparkVehicle(vehicle: Vehicle, exitTime: LocalDateTime): Receipt {
        val ticket = receiptBooth.validateTicket(vehicle.getVehicleTicket())
        if(ticket.getFloorNumberForTicket() >= floors.size) throw FloorDoesNotExistException()
        val floor = floors[ticket.getFloorNumberForTicket()]
        if (floor.clearSpot(ticket.getSpotNumberForTicket())) {
            vehicle.clearTicket()
            return receiptBooth.getReceipt(ticket, exitTime)
        }

        return NullReceipt()
    }
}
