package com.Proyect.SpringBoot.Repository;

import com.Proyect.SpringBoot.Models.Reservations;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface RepositoryReservations extends JpaRepository<Reservations, Long> {
    @Query(value="SELECT * FROM Reservations", nativeQuery = true)
    public List<Reservations> findAll();

    @Query(value="SELECT * FROM Reservations WHERE name_customer=?1 and number_room=?2", nativeQuery = true)
    Optional<Reservations> findReservationsByEveryByEvery(String name_customer, Integer number_room);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Reservations SET name_customer = :name_customer, number_room = :number_room WHERE id = :id", nativeQuery = true)
    void editReservation(@Param("id") int id, @Param("name_customer") String name_customer, @Param("number_room") Integer number_room);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Reservations WHERE id = :id", nativeQuery = true)
    void deleteReservation(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Reservations (name_customer, number_room) VALUES (:name_customer, :number_room)", nativeQuery = true)
    void insertReservation(@Param("name_customer") String name_customer, @Param("number_room") Integer number_room);

    Optional<Reservations> findReservationById(Long id);
}
