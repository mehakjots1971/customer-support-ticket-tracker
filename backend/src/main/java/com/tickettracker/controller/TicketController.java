package com.tickettracker.controller;

import com.tickettracker.dto.TicketRequest;
import com.tickettracker.dto.TicketResponse;
import com.tickettracker.model.Status;
import com.tickettracker.service.TicketService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")

@CrossOrigin(origins = "http://localhost:5173") // Allow requests from Vite dev server
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@Valid @RequestBody TicketRequest request) {
        TicketResponse createdTicket = ticketService.createTicket(request);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAllTickets(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(ticketService.getAllTickets(status, search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TicketResponse> updateTicket(
            @PathVariable Long id,
            @RequestBody java.util.Map<String, Object> updates) {
        TicketResponse updatedTicket = ticketService.updateTicket(id, updates);
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok("Ticket Deleted Successfully");
    }

}
