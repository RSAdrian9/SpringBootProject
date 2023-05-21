package com.Proyect.SpringBoot.Controllers;

import com.Proyect.SpringBoot.Models.Reservations;
import com.Proyect.SpringBoot.Models.Rooms;
import com.Proyect.SpringBoot.Services.InterfacesReservation;
import com.Proyect.SpringBoot.Services.InterfacesRoom;
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
    private final InterfacesReservation reservationService;
    private final InterfacesRoom roomService;

    public ControllerHotel(InterfacesReservation reservationService, InterfacesRoom roomService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
    }

    @GetMapping("/Home")
    public String home(Model model) {
        List<Reservations> reservations = reservationService.findAll();
        List<Rooms> rooms = roomService.findAll();
        model.addAttribute("reservations", reservations);
        model.addAttribute("rooms", rooms);
        return "home";
    }

    @PostMapping("/agregar-reserva")
    public String agregarReserva(
            @RequestParam("arrival_date") String arrivalDate,
            @RequestParam("departure_date") String departureDate,
            @RequestParam("name_customer") String nameCustomer,
            @RequestParam("number_room") int numberRoom,
            @RequestParam("price_total") double priceTotal,
            @RequestParam("state") String state
    ) {
        reservationService.insertReservation(arrivalDate, departureDate, nameCustomer, numberRoom, priceTotal, state);
        return "redirect:/Home";
    }

    @PostMapping("/agregar-habitacion")
    public String agregarHabitacion(
            @RequestParam("available_room") String availableRoom,
            @RequestParam("description") String description,
            @RequestParam("name") String name,
            @RequestParam("number_room") int numberRoom,
            @RequestParam("price_room") double priceRoom,
            @RequestParam("type_room") String typeRoom
    ) {
        roomService.insertRoom(availableRoom, description, name, numberRoom, priceRoom, typeRoom);
        return "redirect:/Home";
    }


    @PostMapping("/asociar-habitaciones")
    public String asociarHabitaciones(
            @RequestParam("reserva_id") long reservaId,
            @RequestParam("habitaciones") List<Long> habitacionesIds
    ) {
        // Obtener la reserva por su ID
        Optional<Reservations> reservaOptional = reservationService.findReservationById(reservaId);
        if (reservaOptional.isPresent()) {
            Reservations reserva = reservaOptional.get();

            // Obtener las habitaciones seleccionadas por sus IDs
            List<Rooms> habitacionesSeleccionadas = roomService.findAllById(habitacionesIds);

            // Asociar las habitaciones a la reserva
            reserva.setRooms(new LinkedHashSet<>(habitacionesSeleccionadas));
            reservationService.save(reserva);
        }

        return "redirect:/Home";
    }

    @PostMapping("/borrar-reserva")
    public String borrarReserva(@RequestParam("reservaBorrar") int id) {
        reservationService.deleteReservation(id);
        return "redirect:/Home";
    }

    @PostMapping("/borrar-habitacion")
    public String borrarHabitacion(@RequestParam("habitacionBorrar") long id) {
        roomService.deleteRoom(id);
        return "redirect:/Home";
    }

    @PostMapping("/actualizar-reserva")
    public String actualizarReserva(
            @RequestParam("reservaActualizar") int id,
            @RequestParam("arrival_date") String arrivalDate,
            @RequestParam("departure_date") String departureDate,
            @RequestParam("name_customer") String nameCustomer,
            @RequestParam("number_room") int numberRoom,
            @RequestParam("price_total") double priceTotal,
            @RequestParam("state") String state
    ) {
        reservationService.editReservation(id, arrivalDate, departureDate, nameCustomer, numberRoom, priceTotal, state);
        return "redirect:/Home";
    }

    @PostMapping("/actualizar-habitacion")
    public String actualizarHabitacion(
            @RequestParam("habitacionActualizar") long id,
            @RequestParam("available_room") String availableRoom,
            @RequestParam("description") String description,
            @RequestParam("name") String name,
            @RequestParam("number_room") int numberRoom,
            @RequestParam("price_room") double priceRoom,
            @RequestParam("type_room") String typeRoom
    ) {
        roomService.editRoom(id, availableRoom, description, name, numberRoom, priceRoom, typeRoom);
        return "redirect:/Home";
    }
}


