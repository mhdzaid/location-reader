package com.api.locationreader.controller;

import com.api.locationreader.dto.LocationResponse;
import com.api.locationreader.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
