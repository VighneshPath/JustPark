package exceptions

class InvalidExitTimeException: Exception("Exit time must be later than entry time")

class SpotDoesNotExistException: Exception("Given spot does not exist")

class TicketDoesNotExistException: Exception("Ticket does not exist")

class ParkingLotFullException: Exception("Parking lot is full")

class NoSpotAvailableException: Exception("No spot available")