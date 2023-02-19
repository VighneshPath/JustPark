package main.exceptions

class SpotNotFoundException: Exception("Spot not found")

class NoSpotAvailableException: Exception("No spot available")

class VehicleAlreadyParkedException: Exception("Vehicle is already parked")

class VehicleIsNotParkedException: Exception("Vehicle is not parked")

class InvalidExitTimeException: Exception("Exit time must be later than entry time")

class SpotDoesNotExistException: Exception("Given spot does not exist")

class TicketDoesNotExistException: Exception("Ticket does not exist")