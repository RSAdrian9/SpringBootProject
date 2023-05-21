package com.Proyect.SpringBoot.Services;

import com.Proyect.SpringBoot.Models.Reservations;

import java.util.List;
import java.util.Optional;

public interface InterfacesReservation {
    void save(Reservations reservations);
    List<Reservations> findAll();
    Optional<Reservations> findReservationsByEvery(String nameCustomer, int numberRoom);
    void editReservation(int id, String arrivalDate, String departureDate, String nameCustomer, int numberRoom, double priceTotal, String state);
    void deleteReservation(int id);
    void insertReservation(String arrivalDate, String departureDate, String nameCustomer, int numberRoom, double priceTotal, String state);

    Optional<Reservations> findReservationById(long id);
}

