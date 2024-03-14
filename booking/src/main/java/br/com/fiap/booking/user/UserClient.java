package br.com.fiap.booking.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("users")
public interface UserClient {

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id);
}
