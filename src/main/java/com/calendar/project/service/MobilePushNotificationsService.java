package com.calendar.project.service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import com.calendar.project.config.HeaderRequestInterceptor;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MobilePushNotificationsService {

    private static final String FIREBASE_SERVER_KEY = "AAAALKcsbUw:APA91bEXPvZYZ-f39WJdA6v6xEOMQpFUWSYbDNLyrcKg5qivCLIlJizm2x3t7JO7VdaiWmtxFLpAHfLkyBobpePdm_MnxqHkMEGy0uYQ9tS0P79ccSC6dFJ9aDdg9HEQEdQl8OpvjePq";
    private static final String FIREBASE_API_URL = "https://my-calendar-project-cf933.firebaseio.com/";

    @Async
    public CompletableFuture<String> send(HttpEntity<String> entity, String topic) {

        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL + topic, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }
}
