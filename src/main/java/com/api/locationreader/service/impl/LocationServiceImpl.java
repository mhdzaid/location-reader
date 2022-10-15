package com.api.locationreader.service.impl;

import com.api.locationreader.dto.LocationCreationRequest;
import com.api.locationreader.dto.LocationResponse;
import com.api.locationreader.mapper.LocationMapper;
import com.api.locationreader.model.Location;
import com.api.locationreader.repository.LocationRepository;
import com.api.locationreader.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class LocationServiceImpl implements LocationService
{
    private final LocationRepository locationRepository;

    private final LocationMapper locationMapper;

    @Override
    public void createLocation(LocationCreationRequest request)
    {
        Location location = locationMapper.locationRequestToLocation(request);
        locationRepository.createLocation(location);
    }

    @Override
    public LocationResponse getLatestLocationOfUser(UUID userId)
    {
        Location location =  locationRepository.getLatestLocationOfUser(userId);
        return locationMapper.locationToLocationResponse(location);
    }

    @Override
    public void deletePartitionByUserId(UUID userId)
    {
        locationRepository.deletePartitionByUserId(userId);
    }

    @Override
    public void createPartitionByUserId(UUID userId)
    {
        locationRepository.createPartitionByUserId(userId);
    }
}
