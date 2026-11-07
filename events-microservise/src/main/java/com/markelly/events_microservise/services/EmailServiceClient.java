package com.markelly.events_microservise.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.markelly.events_microservise.dtos.EmailRequestDTO;

//@FeignClient(name = "email-service", url = "http://endereco-do-servico-de-email")

@FeignClient(name = "email-service", url = "http://localhost:8081/api/email") 
public interface EmailServiceClient {

    @PostMapping("/send")
    void sendEmail(@RequestBody EmailRequestDTO emailRequest);
}