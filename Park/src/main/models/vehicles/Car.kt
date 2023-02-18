package main.models.vehicles

import main.models.Ticket
import main.models.VehicleType
import main.models.VehicleType.CAR

class Car() : Vehicle {
    override val type: VehicleType = CAR
    override var ticket: Ticket? = null
}