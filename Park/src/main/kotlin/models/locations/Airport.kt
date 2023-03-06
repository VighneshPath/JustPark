package models.locations

import exceptions.InvalidFloorNumberException
import exceptions.NoSpotAvailableException
import exceptions.ParkingLotFullException
import exceptions.TicketDoesNotExistException
import main.models.*
import models.*
import models.vehicles.Vehicle
import java.time.LocalDateTime

class Airport(
    override val ticketBooth: TicketBooth, override val receiptBooth: ReceiptBooth,
    floorList: List<Long>
) : Building {
    override val floors: MutableList<Floor> = mutableListOf()

    init {
        for (floorNumber in floorList.indices) {
            floors.add(Floor(floorList[floorNumber], floorNumber+1))
        }
    }

    override fun parkVehicle(vehicle: Vehicle, entryTime: LocalDateTime): Ticket? {
        val floor = getNextAvailableFloor()?: throw ParkingLotFullException()
        val spot = floor.getNextAvailableSpot()?: throw NoSpotAvailableException()

        if(floor.setSpotTo(spot.getSpotsNumber(), vehicle)){
            val ticket = ticketBooth.getTicket(spot.getSpotsNumber(), entryTime, floor.getFloorNumber())
            vehicle.setTicketTo(ticket)
            return ticket
        }
        return null
    }

    override fun unparkVehicle(vehicle: Vehicle, exitTime: LocalDateTime): Receipt? {
        val ticket = vehicle.getVehicleTicket()?: throw TicketDoesNotExistException()
        val floorIndex = ticket.getFloorNumberForTicket() - 1
        if(floorIndex > floors.size) throw InvalidFloorNumberException()
        if(floors[floorIndex].clearSpot(ticket.getSpotNumberForTicket())){
            vehicle.clearTicket()
            return receiptBooth.getReceipt(ticket, exitTime)
        }
        return null
    }

    override fun getNextAvailableFloor(): Floor? {
        floors.forEach{
            val nextSpot = it.getNextAvailableSpot()
            if( nextSpot != null){
                return it
            }
        }
        return null
    }

}