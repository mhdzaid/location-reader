## Location Reader
### Project Structure
* Database `Location` is partitioned based on `user_id` column for fast retrieval since a user can have thousands of entries.
* `created_on` column has been indexed as well for doing time frame query.
* The `id` column is an auto-increment column so the latest location of the user is the last value inserted in the partition.

### How To Run
* To first start the User microservice run the [Server](https://github.com/mhdzaid/server) as well as the [Gateway](https://github.com/mhdzaid/gateway) so that the Eureka Discovery service can register it.
* You would also need to start up the [Location-Writer](https://github.com/mhdzaid/location-writer) microservice as well.
* Run the `PostgreSQL` docker-compose file using the command
    ```
    docker-compose -f util/postgres.yml up -d
    ```
* Run the `ApacheKafka` docker-compose file present in [Location-Writer](https://github.com/mhdzaid/location-writer) microservice.
