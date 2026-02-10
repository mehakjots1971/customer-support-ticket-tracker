package com.tickettracker.repository;

import com.tickettracker.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryTicketRepository implements TicketRepository {

    // Simulating database with thread-safe ConcurrentHashMap
    private final Map<Long, Ticket> ticketStore = new ConcurrentHashMap<>();

    // Simulating auto-increment ID
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Ticket save(Ticket ticket) {
        if (ticket.getId() == null) {
            ticket.setId(idGenerator.getAndIncrement());
        }
        ticketStore.put(ticket.getId(), ticket);
        return ticket;
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return Optional.ofNullable(ticketStore.get(id));
    }

    @Override
    public List<Ticket> findAll() {
        return new ArrayList<>(ticketStore.values());
    }
}
