package com.api.locationreader.controller;

import com.api.locationreader.dto.LocationResponse;
import com.api.locationreader.dto.UserLocationsResponse;
import com.api.locationreader.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class LocationController
{
    private final LocationService locationService;

    @GetMapping("/api/user/{userId}/location/_latest")
    public ResponseEntity<LocationResponse> getLatestUserLocation(@PathVariable String userId)
    {
        LocationResponse response = locationService.getLatestLocationOfUser(UUID.fromString(userId));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/user/{userId}/location")
    public ResponseEntity<UserLocationsResponse> getAllLocationsOfUser(@PathVariable String userId,
                                                                        @RequestParam("page") Integer page,
                                                                        @RequestParam("size") Integer size,
                                                                        @RequestParam("startDate") String startDate,
                                                                        @RequestParam("endDate") String endDate)
    {
        UserLocationsResponse response = locationService.getUserLocationResponse(UUID.fromString(userId),
                LocalDateTime.parse(startDate), LocalDateTime.parse(endDate), page, size);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/api/user/{userId}/location/partition")
    public ResponseEntity<Void> getUserLocationPartition(@PathVariable String userId)
    {
        locationService.createPartitionByUserId(UUID.fromString(userId));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/user/{userId}/location/partition")
    public ResponseEntity<Void> deletePartition(@PathVariable String userId)
    {
        locationService.deletePartitionByUserId(UUID.fromString(userId));
        return ResponseEntity.ok().build();
    }

}
