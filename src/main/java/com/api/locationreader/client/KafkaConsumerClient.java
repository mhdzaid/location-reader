package com.api.locationreader.client;

import com.api.locationreader.model.Location;
import com.api.locationreader.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerClient
{
    private final LocationService locationService;

    /**
     * Receives location data from location-reader microservice
     * @param location
     */
    @KafkaListener(topics = "${location.kafka.location-topic}", containerFactory = "providerKafkaListenerContainerFactory")
    public void kafkaVehicleListener(Location location) {

        locationService.createLocation(location);
    }
}
