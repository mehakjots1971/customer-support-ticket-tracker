package com.tickettracker.dto;

import com.tickettracker.model.Priority;
import com.tickettracker.model.Status;

import java.time.LocalDateTime;

public class TicketResponse {
    private Long id;
    private String customerName;
    private String customerEmail;
    private String subject;
    private String description;
    private Priority priority;
    private Status status;
    private LocalDateTime createdAt;

    public TicketResponse() {
    }

    public TicketResponse(Long id, String customerName, String customerEmail, String subject, String description,
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

    public static TicketResponseBuilder builder() {
        return new TicketResponseBuilder();
    }

    public static class TicketResponseBuilder {
        private Long id;
        private String customerName;
        private String customerEmail;
        private String subject;
        private String description;
        private Priority priority;
        private Status status;
        private LocalDateTime createdAt;

        TicketResponseBuilder() {
        }

        public TicketResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TicketResponseBuilder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public TicketResponseBuilder customerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
            return this;
        }

        public TicketResponseBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public TicketResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TicketResponseBuilder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public TicketResponseBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public TicketResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public TicketResponse build() {
            return new TicketResponse(id, customerName, customerEmail, subject, description, priority, status,
                    createdAt);
        }
    }
}
