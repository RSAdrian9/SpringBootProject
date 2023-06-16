package com.Proyect.SpringBoot.Services;

import com.Proyect.SpringBoot.Models.Rooms;

import java.util.List;
import java.util.Optional;

public interface InterfaceRoom {
    void save(Rooms rooms);
    List<Rooms> findAll();
    Optional<Rooms> findRoomsByEvery(String name, int numberRoom);
    void editRoom(long id, String availableRoom, String description, String name, int numberRoom, double priceRoom, String typeRoom);
    void deleteRoom(long id);
    void insertRoom(String availableRoom, String description, String name, int numberRoom, double priceRoom, String typeRoom);
    List<Rooms> findAllById(List<Integer> ids);
}

