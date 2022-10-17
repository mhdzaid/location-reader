package com.api.locationreader.config;

import com.api.locationreader.model.Location;
import com.api.locationreader.util.LocationProperties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig
{

    String bootstrapServer;
    String groupId;

    public KafkaConsumerConfig(LocationProperties locationProperties)
    {
        bootstrapServer = locationProperties.getKafka().getBootstrapServer();
        groupId = locationProperties.getKafka().getConsumer().getGroupId();
    }

    /**
     * Consumer for Kafka Location Message
     * @return
     */
    public ConsumerFactory<String, Location> locationConsumerFactory()
    {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Location.class);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    /**
     * Listener for Kafka Location Message
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Location> providerKafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String, Location> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(locationConsumerFactory());
        return factory;
    }
}
