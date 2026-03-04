package com.talkevents.jpa.dtos;

import java.util.Set;
import java.util.UUID;

public record SaveAttendeeRecordDto(
        String name,
        String email
) {
}