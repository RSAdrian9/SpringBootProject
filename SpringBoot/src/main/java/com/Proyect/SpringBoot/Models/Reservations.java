package com.Proyect.SpringBoot.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "reservations")
public class Reservations {

    // id, habitacion, cliente, fechaLlegada, fechaSalida, precioTotal, estado

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "numberRoom")
    @JdbcTypeCode(SqlTypes.INTEGER)
    private int numberRoom;

    @Column(name = "nameCustomer")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String nameCustomer;

    @Column(name = "arrivalDate")
    @JdbcTypeCode(SqlTypes.DATE)
    private Date arrivalDate;

    @Column(name = "departureDate")
    @JdbcTypeCode(SqlTypes.DATE)
    private Date departureDate;

    @Column(name = "priceTotal")
    @JdbcTypeCode(SqlTypes.DOUBLE)
    private double priceTotal;

    @Column(name = "state")
    @JdbcTypeCode(SqlTypes.LONGNVARCHAR)
    private String state;

    @ManyToMany
    @JoinTable(
            name = "reservation_rooms",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private List<Rooms> reservationses = new ArrayList<>();

    // Una reserva pertenece a una habitación

}
