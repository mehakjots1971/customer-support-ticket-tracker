package com.tickettracker.model;

import java.time.LocalDateTime;

import java.time.LocalDateTime;

public class Ticket {
    private Long id;
    private String customerName;
    private String customerEmail;
    private String subject;
    private String description;
    private Priority priority;
    private Status status;
    private LocalDateTime createdAt;

    public Ticket() {
    }

    public Ticket(Long id, String customerName, String customerEmail, String subject, String description,
            Priority priority, Status status, LocalDateTime createdAt) {
        this.id = id;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.subject = subject;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static TicketBuilder builder() {
        return new TicketBuilder();
    }

    public static class TicketBuilder {
        private Long id;
        private String customerName;
        private String customerEmail;
        private String subject;
        private String description;
        private Priority priority;
        private Status status;
        private LocalDateTime createdAt;

        TicketBuilder() {
        }

        public TicketBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TicketBuilder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public TicketBuilder customerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
            return this;
        }

        public TicketBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public TicketBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TicketBuilder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public TicketBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public TicketBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Ticket build() {
            return new Ticket(id, customerName, customerEmail, subject, description, priority, status, createdAt);
        }
    }
}
