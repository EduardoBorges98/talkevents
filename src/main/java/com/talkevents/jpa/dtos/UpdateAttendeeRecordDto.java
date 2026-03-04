package com.talkevents.jpa.dtos;

import java.util.Set;
import java.util.UUID;

public record UpdateAttendeeRecordDto(
        String name,
        String email,
        UUID id
) {
}