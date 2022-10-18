package com.api.locationreader.repository.impl;

import com.api.locationreader.model.Location;
import com.api.locationreader.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public class LocationRepositoryImpl implements LocationRepository
{
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Create location with incremental primary key id
     * @param location
     */
    @Override
    public void createLocation(Location location)
    {
        jdbcTemplate.update("insert into location " +
                "(latitude, longitude, created_on, user_id) VALUES (?, ?, ?, ?) ;",
                  location.getLatitude(), location.getLongitude(), location.getCreatedOn(), location.getUserId());

    }

    /**
     * Get latest location of user which would be the last inserted row in the partition
     * @param userId
     * @return
     */
    @Override
    public Location getLatestLocationOfUser(UUID userId)
    {
        String query = "select * from location where user_id = ? order by id desc limit 1";
        Location location = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Location.class), userId);
        return location;
    }

    /**
     * Create partition when user is created
     * @param userId
     */
    @Override
    public void createPartitionByUserId(UUID userId)
    {
        String partitionTableName = "location_partition_" + userId.toString().replace('-', '_');
        String sqlQuery = "CREATE TABLE IF NOT EXISTS "+partitionTableName+" PARTITION OF location FOR VALUES IN ('"+userId.toString()+"')";
        jdbcTemplate.execute(sqlQuery);
    }

    /**
     * Delete partition of a user incase user is deleted
     * @param userId
     */
    @Override
    public void deletePartitionByUserId(UUID userId)
    {
        String partitionTableName = "location_partition_" + userId.toString().replace('-', '_');
        String sqlQuery = "DROP TABLE IF EXISTS "+partitionTableName+" cascade";
        jdbcTemplate.execute(sqlQuery);
    }

    /**
     * Request created using JdbcTemplate to get all locations of user in a time frame
     * @param userId
     * @param startDate
     * @param endDate
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Location> getAllLocationsByUserIdAndDateRange(UUID userId, LocalDateTime startDate, LocalDateTime endDate, Integer page, Integer size)
    {
        String query = "select * from location where user_id = ? and created_on >= ? and created_on <= ? limit ? offset ?";
        List<Location> locations = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Location.class), userId, startDate, endDate, size, page);
        return locations;
    }

}
