package org.example.agroshare2.controllers;

import org.example.agroshare2.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping
    public UserDTO hello() {
        return new UserDTO("Витя", "27");
    }

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "Hello World";
    }
}
