package com.telusko.HospitalManagementSystem.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class WebHookService
{
    private final RestTemplate restTemplate;

    //Constructor Injection
    public WebHookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendWebHook(String webHookUrl, Map<String, Object> payload)
    {
        restTemplate.postForObject(webHookUrl,payload,String.class);
        //Kuthe req pathavaycha , JSON data , response type
    }
}
