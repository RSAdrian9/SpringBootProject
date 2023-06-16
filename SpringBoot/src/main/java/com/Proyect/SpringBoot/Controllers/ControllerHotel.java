package com.Proyect.SpringBoot.Controllers;

import com.Proyect.SpringBoot.Models.Reservations;
import com.Proyect.SpringBoot.Models.Rooms;
import com.Proyect.SpringBoot.Services.InterfaceReservation;
import com.Proyect.SpringBoot.Services.InterfaceRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

@Controller
public class ControllerHotel {

    @Autowired
    private InterfaceReservation ReservationService;

    @Autowired
    private InterfaceRoom RoomService;

    public ControllerHotel(InterfaceReservation ReservationService, InterfaceRoom RoomService) {
        this.ReservationService = ReservationService;
        this.RoomService = RoomService;
    }

    @GetMapping("/hotel")
    public String hotel(Model model) {
        List<Reservations> reservations = ReservationService.findAll();
        List<Rooms> rooms = RoomService.findAll();
        model.addAttribute("reservations", reservations);
        model.addAttribute("rooms", rooms);
        return "hotel";
    }

    @PostMapping("/agregar-reserva")
    public String agregarReserva(
            @RequestParam("arrivalDate") String arrivalDate,
            @RequestParam("departureDate") String departureDate,
            @RequestParam("nameCustomer") String nameCustomer,
            @RequestParam("numberRoomReservation") int numberRoomReservation,
            @RequestParam("priceTotal") double priceTotal,
            @RequestParam("state") String state
    ) {
        ReservationService.insertReservation(arrivalDate, departureDate, nameCustomer, numberRoomReservation, priceTotal, state);
        return "redirect:/hotel";
    }

    @PostMapping("/agregar-habitacion")
    public String agregarHabitacion(
            @RequestParam("availableRoom") String availableRoom,
            @RequestParam("description") String description,
            @RequestParam("name") String name,
            @RequestParam("numberRoom") int numberRoom,
            @RequestParam("priceRoom") double priceRoom,
            @RequestParam("typeRoom") String typeRoom
    ) {
        RoomService.insertRoom(availableRoom, description, name, numberRoom, priceRoom, typeRoom);
        return "redirect:/hotel";
    }


    @PostMapping("/asociar-habitaciones")
    public String asociarHabitaciones(
            @RequestParam("reservaId") int reservaId,
            @RequestParam("habitacionesIds") List<Integer> habitacionesIds
    ) {
        // Obtener la reserva por su ID
        Optional<Reservations> reservaOptional = ReservationService.findReservationById((int) reservaId);
        if (reservaOptional.isPresent()) {
            Reservations reserva = reservaOptional.get();

            // Obtener las habitaciones seleccionadas por sus IDs
            List<Rooms> habitacionesSeleccionadas = RoomService.findAllById(habitacionesIds);

            /*
            // Asociar las habitaciones a la reserva
            reserva.setRooms(new LinkedHashSet<>(habitacionesSeleccionadas));
            ReservationService.save(reserva);

             */
        }

        return "redirect:/hotel";
    }

    @PostMapping("/borrar-reserva")
    public String borrarReserva(@RequestParam("reservaBorrar") int id) {
        ReservationService.deleteReservation(id);
        return "redirect:/hotel";
    }

    @PostMapping("/borrar-habitacion")
    public String borrarHabitacion(@RequestParam("habitacionBorrar") int id) {
        RoomService.deleteRoom(id);
        return "redirect:/hotel";
    }

    @PostMapping("/actualizar-reserva")
    public String actualizarReserva(
            @RequestParam("reservaActualizar") int id,
            @RequestParam("arrivalDate") String arrivalDate,
            @RequestParam("departureDate") String departureDate,
            @RequestParam("nameCustomer") String nameCustomer,
            @RequestParam("numberRoom") int numberRoom,
            @RequestParam("priceTotal") double priceTotal,
            @RequestParam("state") String state
    ) {
        ReservationService.editReservation(id, arrivalDate, departureDate, nameCustomer, numberRoom, priceTotal, state);
        return "redirect:/hotel";
    }

    @PostMapping("/actualizar-habitacion")
    public String actualizarHabitacion(
            @RequestParam("habitacionActualizar") long id,
            @RequestParam("availableRoom") String availableRoom,
            @RequestParam("description") String description,
            @RequestParam("name") String name,
            @RequestParam("numberRoom") int numberRoom,
            @RequestParam("priceRoom") double priceRoom,
            @RequestParam("typeRoom") String typeRoom
    ) {
        RoomService.editRoom(id, availableRoom, description, name, numberRoom, priceRoom, typeRoom);
        return "redirect:/hotel";
    }
}


