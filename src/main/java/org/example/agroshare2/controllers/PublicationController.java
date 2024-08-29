package org.example.agroshare2.controllers;

import org.example.agroshare2.entities.Publication;
import org.example.agroshare2.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/publications")
public class PublicationController {
    @Autowired
    private PublicationService publicationService;

    @GetMapping("/list/last")
    public List<Publication> listLastPublication() {
        return publicationService.getLastPublications();
    }

    @GetMapping("/find")
    public List<Publication> findPublicationByTitle(@RequestParam String title) {
        return publicationService.findByTitle(title);
    }
}
