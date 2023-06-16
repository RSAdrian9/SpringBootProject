package com.Proyect.SpringBoot.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "rooms")
public class Rooms {

    // id, numero, tipo, precioPorNoche, descripcion, disponible

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int Id;

    @Column(name = "name")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String name;

    @Column(name = "numberRoom")
    @JdbcTypeCode(SqlTypes.INTEGER)
    private int numberRoom;

    @Column(name = "typeRoom")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String typeRoom;

    @Column(name = "priceRoom")
    @JdbcTypeCode(SqlTypes.DOUBLE)
    private double priceRoom;

    @Column(name = "description")
    @JdbcTypeCode(SqlTypes.LONG32NVARCHAR)
    private String description;

    @Column(name = "availableRoom")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String availableRoom;

    @ManyToOne
    @JoinColumn(name = "reservations_id")
    private Reservations reservations;


    // Una habitación puede ser reservada por uno o muchos usuarios



}
