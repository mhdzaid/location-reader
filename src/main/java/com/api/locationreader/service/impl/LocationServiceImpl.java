package com.api.locationreader.service.impl;

import com.api.locationreader.dto.LocationResponse;
import com.api.locationreader.dto.UserLocationsResponse;
import com.api.locationreader.mapper.LocationMapper;
import com.api.locationreader.model.Location;
import com.api.locationreader.repository.LocationRepository;
import com.api.locationreader.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class LocationServiceImpl implements LocationService
{
    private final LocationRepository locationRepository;

    private final LocationMapper locationMapper;

    /**
     * Create Location
     * @param request
     */
    @Override
    public void createLocation(Location request)
    {
        locationRepository.createLocation(request);
    }

    /**
     * Get latest user location
     * @param userId
     * @return
     */
    @Override
    public LocationResponse getLatestLocationOfUser(UUID userId)
    {
        Location location =  locationRepository.getLatestLocationOfUser(userId);
        return locationMapper.locationToLocationResponse(location);
    }

    /**
     * Get all locations of a user in a given time frame
     * @param userId
     * @param startDate
     * @param endDate
     * @param page
     * @param size
     * @return
     */
    @Override
    public UserLocationsResponse getUserLocationResponse(UUID userId, LocalDateTime startDate, LocalDateTime endDate,
                                                         Integer page, Integer size) {
        List<Location> locations = locationRepository.getAllLocationsByUserIdAndDateRange(userId, startDate, endDate, page, size);
        return locationMapper.createFromLocationsAndUser(userId, locations);
    }

    /**
     * Delete partition
     * @param userId
     */
    @Override
    public void deletePartitionByUserId(UUID userId)
    {
        locationRepository.deletePartitionByUserId(userId);
    }

    /**
     * Create partition
     * @param userId
     */
    @Override
    public void createPartitionByUserId(UUID userId)
    {
        locationRepository.createPartitionByUserId(userId);
    }
}
