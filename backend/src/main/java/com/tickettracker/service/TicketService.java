package com.tickettracker.service;

import com.tickettracker.dto.TicketRequest;
import com.tickettracker.dto.TicketResponse;
import com.tickettracker.exception.InvalidTransitionException;
import com.tickettracker.exception.ResourceNotFoundException;
import com.tickettracker.model.Status;
import com.tickettracker.model.Ticket;
import com.tickettracker.repository.TicketRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public TicketResponse createTicket(TicketRequest request) {
        Ticket ticket = Ticket.builder()
                .customerName(request.getCustomerName())
                .customerEmail(request.getCustomerEmail())
                .subject(request.getSubject())
                .description(request.getDescription())
                .priority(request.getPriority())
                .status(Status.OPEN)
                .createdAt(LocalDateTime.now())
                .build();

        Ticket savedTicket = ticketRepository.save(ticket);
        return mapToResponse(savedTicket);
    }

    public List<TicketResponse> getAllTickets(Status status, String search) {

        List<Ticket> tickets = ticketRepository.findAll();

        if (status != null) {
            tickets = tickets.stream()
                    .filter(t -> t.getStatus() == status)
                    .collect(Collectors.toList());
        }

        if (search != null && !search.isEmpty()) {
            tickets = tickets.stream()
                    .filter(t -> t.getSubject().toLowerCase()
                            .contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }

        return tickets.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    public TicketResponse getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));
        return mapToResponse(ticket);
    }

    public TicketResponse updateTicket(Long id, java.util.Map<String, Object> updates) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));

        if (updates.containsKey("status")) {
            String statusStr = (String) updates.get("status");
            Status newStatus = Status.valueOf(statusStr);
            validateTransition(ticket.getStatus(), newStatus);
            ticket.setStatus(newStatus);
        }

        // Add other field updates here if needed in the future

        Ticket updatedTicket = ticketRepository.save(ticket);
        return mapToResponse(updatedTicket);
    }

    private void validateTransition(Status currentStatus, Status newStatus) {
        if (currentStatus == Status.OPEN && newStatus == Status.RESOLVED) {
            throw new InvalidTransitionException("Cannot bypass IN_PROGRESS state. Move to IN_PROGRESS first.");
        }

        // Add more specific transition rules if needed
        // For example: preventing moving back from RESOLVED to OPEN without specific
        // logic
    }

    public void deleteTicket(Long id){
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Ticket not found"));

        ticketRepository.delete(id);
    }

    private TicketResponse mapToResponse(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .customerName(ticket.getCustomerName())
                .customerEmail(ticket.getCustomerEmail())
                .subject(ticket.getSubject())
                .description(ticket.getDescription())
                .priority(ticket.getPriority())
                .status(ticket.getStatus())
                .createdAt(ticket.getCreatedAt())
                .build();
    }
}
