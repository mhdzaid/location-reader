package com.api.locationreader.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LocationCreationRequest implements Serializable
{
    private UUID userId;

    private LocalDateTime createdOn;

    private LocationResponse location;
}
