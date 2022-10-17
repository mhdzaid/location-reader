package com.api.locationreader.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter
@Component
@ConfigurationProperties(prefix = "location", ignoreUnknownFields = false)
public class LocationProperties {

    private final Kafka kafka = new Kafka();

    @Getter
    public static class Kafka {
        private final Consumer consumer = new Consumer();
        public String bootstrapServer;
        public String locationTopic;

        @Getter
        public static class Consumer {
            public String groupId;
        }
    }
}
