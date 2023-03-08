package exceptions

class InvalidExitTimeException : Exception("Exit time must be later than entry time")

class SpotDoesNotExistException : Exception("Given spot does not exist")

class TicketDoesNotExistException : Exception("Ticket does not exist")

class FloorDoesNotExistException : Exception("Floor does not exist")

class InvalidTicketException : Exception("Ticket is not valid")

class CannotParkVehicleInLocationException: Exception("Cannot park vehicle in location")