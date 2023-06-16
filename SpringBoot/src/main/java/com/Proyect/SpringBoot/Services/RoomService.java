package com.Proyect.SpringBoot.Services;

import com.Proyect.SpringBoot.Models.Rooms;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements InterfaceRoom {
    @Override
    public void save(Rooms rooms) {

    }

    @Override
    public List<Rooms> findAll() {
        return null;
    }

    @Override
    public Optional<Rooms> findRoomsByEvery(String name, int numberRoom) {
        return Optional.empty();
    }

    @Override
    public void editRoom(long id, String availableRoom, String description, String name, int numberRoom, double priceRoom, String typeRoom) {

    }

    @Override
    public void deleteRoom(long id) {

    }

    @Override
    public void insertRoom(String availableRoom, String description, String name, int numberRoom, double priceRoom, String typeRoom) {

    }

    @Override
    public List<Rooms> findAllById(List<Integer> ids) {
        return null;
    }
}
