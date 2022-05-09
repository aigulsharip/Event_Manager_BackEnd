package kz.daracademy.commentapi.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

    @Configuration
    public class ElasticConfig extends AbstractElasticsearchConfiguration {

        @Override
        public RestHighLevelClient elasticsearchClient() {
            ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                    .connectedTo("")
                    .usingSsl()
                    .withBasicAuth("elastic", "")
                    .build();

            return RestClients.create(clientConfiguration).rest();
        }
    }


