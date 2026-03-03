package com.talkevents.jpa.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Session")
public class Session implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;


}