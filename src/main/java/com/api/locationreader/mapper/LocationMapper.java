package com.api.locationreader.mapper;

import com.api.locationreader.dto.LocationCreationRequest;
import com.api.locationreader.dto.LocationInfo;
import com.api.locationreader.dto.LocationResponse;
import com.api.locationreader.dto.UserLocationsResponse;
import com.api.locationreader.model.Location;
import org.mapstruct.Mapper;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface LocationMapper
{
    Location locationRequestToLocation(LocationCreationRequest locationCreationRequest);

    LocationResponse locationToLocationResponse(Location location);

    default UserLocationsResponse createFromLocationsAndUser(UUID userId, List<Location> locationList)
    {
        List<LocationInfo> locations = new ArrayList<>();
        if(CollectionUtils.isEmpty(locationList))
            return new UserLocationsResponse(userId, locations);
        for(Location location: locationList)
        {
            LocationResponse locationResponse = new LocationResponse(location.getLatitude(), location.getLongitude());
            LocationInfo locationInfo = new LocationInfo(location.getCreatedOn(), locationResponse);
            locations.add(locationInfo);
        }
        return new UserLocationsResponse(userId, locations);
    }
}
