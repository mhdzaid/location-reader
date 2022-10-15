package com.api.locationreader.repository.impl;

import com.api.locationreader.model.Location;
import com.api.locationreader.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class LocationRepositoryImpl implements LocationRepository
{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void createLocation(Location location)
    {
        jdbcTemplate.update("insert into location " +
                "(id, latitude, longitude, created_on, userId) VALUES (?, ?, ?, ?, ?) RETURNING id;",
                  location.getId(), location.getLatitude(), location.getLongitude(), location.getCreatedOn(), location.getUserId());

    }

    @Override
    public Location getLatestLocationOfUser(UUID userId)
    {
        String query = "select * from location where user_id = ? order by id limit 1";
        Location location = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Location.class), userId);
        return location;
    }

    @Override
    public void createPartitionByUserId(UUID userId)
    {
        String partitionTableName = "location_partition_" + userId.toString().replace('-', '_');
        String sqlQuery = "CREATE TABLE IF NOT EXISTS "+partitionTableName+" PARTITION OF location FOR VALUES IN ('"+userId.toString()+"')";
        jdbcTemplate.execute(sqlQuery);
    }

    @Override
    public void deletePartitionByUserId(UUID userId)
    {
        String partitionTableName = "location_partition_" + userId.toString().replace('-', '_');
        String sqlQuery = "DROP TABLE IF EXISTS "+partitionTableName+" cascade";
        jdbcTemplate.execute(sqlQuery);
    }

}
