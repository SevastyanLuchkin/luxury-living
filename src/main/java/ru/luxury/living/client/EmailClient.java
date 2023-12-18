package ru.luxury.living.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "email", url = "${email.api.host}", path = "ru/api")
public interface EmailClient {

    @PostMapping("subscribe")
    void subscribe(@RequestParam String apiKey, @RequestParam("fields[email]") String email);
}
