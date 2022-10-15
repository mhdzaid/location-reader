package com.api.locationreader.service;

import com.api.locationreader.dto.LocationCreationRequest;
import com.api.locationreader.dto.LocationResponse;
import com.api.locationreader.model.Location;

import java.util.UUID;

public interface LocationService
{
    void createLocation(LocationCreationRequest request);

    LocationResponse getLatestLocationOfUser(UUID userId);

    void deletePartitionByUserId(UUID userId);

    void createPartitionByUserId(UUID userId);
}
