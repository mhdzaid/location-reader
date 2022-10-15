package com.api.locationreader.repository;

import com.api.locationreader.model.Location;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface LocationRepository
{
    void createLocation(Location location);

    Location getLatestLocationOfUser(UUID userId);

    void createPartitionByUserId(UUID userId);

    void deletePartitionByUserId(UUID userId);

    List<Location> getAllLocationsByUserIdAndDateRange(UUID userId, LocalDateTime startDate, LocalDateTime endDate, Integer page, Integer size);
}
