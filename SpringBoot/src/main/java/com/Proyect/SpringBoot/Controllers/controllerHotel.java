package com.Proyect.SpringBoot.Controllers;

import com.Proyect.SpringBoot.Models.reservations;
import com.Proyect.SpringBoot.Models.rooms;
import com.Proyect.SpringBoot.Services.iReservation;
import com.Proyect.SpringBoot.Services.iRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class controllerHotel {

    @Autowired
    private iReservation ReservationService;

    @Autowired
    private iRoom RoomService;

    public controllerHotel(iReservation ReservationService, iRoom RoomService) {
        this.ReservationService = ReservationService;
        this.RoomService = RoomService;
    }

    @GetMapping("/hotel")
    public String hotel(Model model) {
        List<reservations> reservations = ReservationService.findAll();
        List<rooms> rooms = RoomService.findAll();
        model.addAttribute("reservations", reservations);
        model.addAttribute("rooms", rooms);
        return "hotel";
    }

    @PostMapping("/agregar-reserva")
    public String agregarReserva(
            @RequestParam("number_room") int number_room,
            @RequestParam("name_customer") String name_customer,
            @RequestParam("arrival_date") LocalDate arrival_date,
            @RequestParam("departure_date") LocalDate departure_date,
            @RequestParam("price_total") double price_total
    ) {
        ReservationService.insertReservation(number_room, name_customer, arrival_date, departure_date, price_total);
        return "redirect:/hotel";
    }

    @PostMapping("/agregar-habitacion")
    public String agregarHabitacion(
            @RequestParam("number_room") int number_room,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price_room") double price_room
    ) {
        RoomService.insertRoom(number_room, name, description, price_room);
        return "redirect:/hotel";
    }


    @PostMapping("/asociar-habitaciones")
    public String asociarHabitaciones(
            @RequestParam("reservaId") int reservaId,
            @RequestParam("habitacionesIds") List<Integer> habitacionesIds
    ) {
        // Obtener la reserva por su ID
        Optional<reservations> reservaOptional = ReservationService.findReservationById((int) reservaId);
        if (reservaOptional.isPresent()) {
            reservations reserva = reservaOptional.get();

            // Obtener las habitaciones seleccionadas por sus IDs
            List<rooms> habitacionesSeleccionadas = RoomService.findAllById(habitacionesIds);

            /*
            // Asociar las habitaciones a la reserva
            reserva.setRooms(new LinkedHashSet<>(habitacionesSeleccionadas));
            ReservationService.save(reserva);

             */
        }

        return "redirect:/hotel";
    }

    @PostMapping("/borrar-reserva")
    public String borrarReserva(@RequestParam("idborrarReserva") int id) {
        ReservationService.deleteReservation(id);
        return "redirect:/hotel";
    }

    @PostMapping("/borrar-habitacion")
    public String borrarHabitacion(@RequestParam("idborrarHabitacion") int id) {
        RoomService.deleteRoom(id);
        return "redirect:/hotel";
    }

    @PostMapping("/actualizar-reserva")
    public String actualizarReserva(
            @RequestParam("id") int id,
            @RequestParam("number_room") int number_room,
            @RequestParam("name_customer") String name_customer,
            @RequestParam("arrival_date") LocalDate arrival_date,
            @RequestParam("departure_date") LocalDate departure_date,
            @RequestParam("price_total") double price_total
    ) {
        ReservationService.editReservation(id, number_room, name_customer, arrival_date, departure_date, price_total);
        return "redirect:/hotel";
    }

    @PostMapping("/actualizar-habitacion")
    public String actualizarHabitacion(
            @RequestParam("idactualizarhabitacion") int id,
            @RequestParam("number_room") int number_room,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price_room") double price_room
    ) {
        RoomService.editRoom(id, number_room, name, description, price_room);
        return "redirect:/hotel";
    }
}


