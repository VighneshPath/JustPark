package models

import exceptions.CannotParkVehicleInLocationException
import models.LocationType.*
import models.VehicleType.HEAVY_VEHICLE
import models.receipts.Receipt
import models.tickets.NullTicket
import models.tickets.Ticket
import java.time.LocalDateTime

class Location(
    private val ticketBooth: TicketBooth,
    private val receiptBooth: ReceiptBooth,
    private val floorTracker: FloorTracker,
    private val locationType: LocationType = MALL
) {
    private fun checkVehicleTypeBeforeParking(vehicleType: VehicleType){
        if(unsupportedType(vehicleType)){
            throw CannotParkVehicleInLocationException()
        }
    }

    private fun unsupportedType(vehicleType: VehicleType) =
        vehicleType == HEAVY_VEHICLE && (locationType == AIRPORT || locationType == STADIUM)

    fun parkVehicle(vehicle: Vehicle, entryTime: LocalDateTime): Ticket {
        checkVehicleTypeBeforeParking(vehicle.getVehicleType())
        val floor = floorTracker.getNextAvailableFloor(vehicle.getVehicleType()) ?: return NullTicket()
        val spot = floor.getNextAvailableSpot(vehicle.getVehicleType()) ?: return NullTicket()

        floorTracker.parkVehicleAt(floor.getFloorNumber(), spot.getSpotsNumber(), vehicle)

        val ticket = ticketBooth.getTicket(
            floor.getFloorNumber(),
            spot.getSpotsNumber(),
            entryTime
        )
        vehicle.setTicketTo(ticket)

        return ticket
    }

    fun unparkVehicle(vehicle: Vehicle, exitTime: LocalDateTime): Receipt {
        val ticket = receiptBooth.validateTicket(vehicle.getVehicleTicket())
        val floor = floorTracker.getFloor(ticket.getFloorNumberForTicket())

        floorTracker.unparkVehicleFrom(floor.getFloorNumber(), ticket.getSpotNumberForTicket())

        val receipt = receiptBooth.getReceipt(vehicle, exitTime)
        vehicle.clearTicket()

        return receipt
    }
}
