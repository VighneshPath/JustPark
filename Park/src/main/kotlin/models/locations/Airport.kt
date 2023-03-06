package models.locations

import exceptions.NoSpotAvailableException
import exceptions.ParkingLotFullException
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
        spot.reserveSpot(vehicle)
        return ticketBooth.getTicket(spot.getSpotsNumber(), entryTime, floor.getFloorNumber())
    }

    override fun unparkVehicle(vehicle: Vehicle, exitTime: LocalDateTime): Receipt? {
        TODO("Not yet implemented")
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