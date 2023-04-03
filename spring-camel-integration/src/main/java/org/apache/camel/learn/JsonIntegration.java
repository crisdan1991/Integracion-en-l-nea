package org.apache.camel.learn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

class JsonIntegration implements AggregationStrategy {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exchange aggregate(Exchange original, Exchange resource) {
        try {
            String firstQueryResult = original.getIn().getBody(String.class);
            String secondQueryResult = resource.getIn().getBody(String.class);

            List<Map<Object, Object>> firstJson = objectMapper.readValue(firstQueryResult,
                    new TypeReference<List<Map<Object, Object>>>() {
                    });
            List<Map<Object, Object>> secondJson = objectMapper.readValue(secondQueryResult,
                    new TypeReference<List<Map<Object, Object>>>() {
                    });

            firstJson.addAll(secondJson);

            String combinedResult = objectMapper.writeValueAsString(firstJson);
            original.getIn().setBody(combinedResult);

            return original;
        } catch (Exception e) {
            throw new RuntimeException("Error al combinar los resultados JSON", e);
        }
    }
}
