package com.fraus.hotel.exception;

public class RoomAlreadyReservedException extends RuntimeException {
    public RoomAlreadyReservedException(String message) {
        super(message);
    }
}
