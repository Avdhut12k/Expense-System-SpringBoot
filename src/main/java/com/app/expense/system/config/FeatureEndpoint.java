package com.app.expense.system.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "features")
public class FeatureEndpoint {

    private final Map<String,Feature> featureMap = new ConcurrentHashMap<>();

    public FeatureEndpoint() {
        featureMap.put("Notification-system",new Feature(false));
        featureMap.put("Expense-System",new Feature(true));
        featureMap.put("Authentication",new Feature(false));
    }

    @ReadOperation
    public Feature getFeature(@Selector String featureName) {
        return featureMap.get(featureName);
    }

    @ReadOperation
    public Map<String,Feature> getFeatures() {
        return featureMap;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Feature{
        private boolean isEnabled;
    }

}
