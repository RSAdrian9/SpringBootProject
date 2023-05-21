package com.Proyect.SpringBoot.Repository;

import com.Proyect.SpringBoot.Models.Rooms;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface RepositoryRooms extends JpaRepository<Rooms, Long> {
    @Query(value="SELECT * FROM Rooms", nativeQuery = true)
    public List<Rooms> findAll();

    @Query(value="SELECT * FROM Rooms WHERE name=?1 and number_room=?2", nativeQuery = true)
    Optional<Rooms> findRoomsByEvery(String name, int number_room);

    @Modifying
    @Transactional
    @Query(value="UPDATE Rooms SET name = :name, number_room = :number_room WHERE id = :id", nativeQuery = true)
    void editRoom(@Param("id") long id, @Param("name") String name, @Param("number_room") int number_room);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Rooms WHERE id = :id", nativeQuery = true)
    void deleteRoom(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Rooms (name, number_room) VALUES (:name, :number_room)", nativeQuery = true)
    void insertRoom(@Param("name") String name, @Param("number_room") int number_room);

    List<Rooms> findAllByIdIn(List<Long> ids);
}
