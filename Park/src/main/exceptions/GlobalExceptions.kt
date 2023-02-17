package main.exceptions

class SpotNotFoundException: Exception("Spot not found")

class NoSpotAvailableException: Exception("No spot available")

class VehicleAlreadyParkedException: Exception("Vehicle is already parked")

class VehicleIsNotParkedException: Exception("Vehicle is not parked")

