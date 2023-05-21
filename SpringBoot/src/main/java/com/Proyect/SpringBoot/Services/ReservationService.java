package com.Proyect.SpringBoot.Services;

import com.Proyect.SpringBoot.Models.Reservations;
import com.Proyect.SpringBoot.Repository.RepositoryReservations;

import java.util.List;
import java.util.Optional;

public class ReservationService implements InterfacesReservation {

    private final RepositoryReservations reservationRepository;

    public ReservationService(RepositoryReservations reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void save(Reservations reservations) {
        reservationRepository.save(reservations);
    }

    @Override
    public List<Reservations> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservations> findReservationsByEvery(String nameCustomer, int numberRoom) {
        return Optional.empty();
    }

    @Override
    public void editReservation(int id, String arrivalDate, String departureDate, String nameCustomer, int numberRoom, double priceTotal, String state) {

    }

    @Override
    public void deleteReservation(int id) {

    }

    @Override
    public void insertReservation(String arrivalDate, String departureDate, String nameCustomer, int numberRoom, double priceTotal, String state) {

    }

    public Optional<Reservations> findReservationsByEveryByEvery(String nameCustomer, int numberRoom) {
        return reservationRepository.findReservationsByEveryByEvery(nameCustomer, numberRoom);
    }

    public void editReservation(int id, String nameCustomer, int numberRoom) {
        Optional<Reservations> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservations reservation = optionalReservation.get();
            reservation.setNameCustomer(nameCustomer);
            reservation.setNumberRoom(numberRoom);
            reservationRepository.save(reservation);
        }
    }


    public void insertReservation(String nameCustomer, int numberRoom) {
        Reservations reservation = new Reservations();
        reservation.setNameCustomer(nameCustomer);
        reservation.setNumberRoom(numberRoom);
        reservationRepository.save(reservation);
    }

    @Override
    public Optional<Reservations> findReservationById(long id) {
        return reservationRepository.findById(id);
    }
}

