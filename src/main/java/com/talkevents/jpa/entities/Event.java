package com.talkevents.jpa.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Event")
public class Event implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @Column(nullable=false)
    private String name;

    @Column()
    private LocalDateTime date;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
