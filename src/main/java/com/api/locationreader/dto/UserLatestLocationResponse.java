package com.api.locationreader.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLatestLocationResponse
{
    private UUID userId;

    private LocalDateTime createdOn;

    private String email;

    private String firstName;

    private String secondName;

    LocationResponse location;
}
