package org.example.agroshare2.controllers;

import org.example.agroshare2.dto.PersonDto;
import org.example.agroshare2.entities.User;
import org.example.agroshare2.services.PublicationService;
import org.example.agroshare2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final PublicationService publicationService;

    public UserController(UserService userService, PublicationService publicationService) {
        this.userService = userService;
        this.publicationService = publicationService;
    }

    @GetMapping("/get/{id}")
    public PersonDto get(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/{id}/set-image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String uploadFile(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) throws IOException {
        userService.addImageToProfile(id, file);

        return "huy";
    }
}
