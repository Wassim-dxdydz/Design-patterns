package fr.uga.miage.m1.weather

class ConnectionException(override val message: String) : Exception(message)
class UnknownException(override val message: String) : Exception(message)