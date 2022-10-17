package com.api.locationreader.service;

import com.api.locationreader.dto.LocationCreationRequest;
import com.api.locationreader.dto.LocationResponse;
import com.api.locationreader.dto.UserLocationsResponse;
import com.api.locationreader.model.Location;

import java.time.LocalDateTime;
import java.util.UUID;

public interface LocationService
{
    void createLocation(Location request);

    LocationResponse getLatestLocationOfUser(UUID userId);

    UserLocationsResponse getUserLocationResponse(UUID userId, LocalDateTime startDate, LocalDateTime endDate,
                                                  Integer page, Integer size);

    void deletePartitionByUserId(UUID userId);

    void createPartitionByUserId(UUID userId);
}
