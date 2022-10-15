package com.api.locationreader.mapper;

import com.api.locationreader.dto.LocationCreationRequest;
import com.api.locationreader.dto.LocationResponse;
import com.api.locationreader.dto.UserLocationsResponse;
import com.api.locationreader.model.Location;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface LocationMapper
{
    Location locationRequestToLocation(LocationCreationRequest locationCreationRequest);

    LocationResponse locationToLocationResponse(Location location);

    UserLocationsResponse createFromLocationsAndUser(UUID userId, List<Location> locationList);
}
