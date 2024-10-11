package org.example.agroshare2.controllers;

import org.example.agroshare2.dto.PublicationDto;
import org.example.agroshare2.entities.Category;
import org.example.agroshare2.entities.Publication;
import org.example.agroshare2.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/publications")
public class PublicationController {
    @Autowired
    private PublicationService publicationService;

    @GetMapping("/list/last")
    public List<PublicationDto> listLastPublication() {
        return publicationService.getLastPublications();
    }

    @GetMapping("/find")
    public List<Publication> findPublicationByTitle(@RequestParam String title) {
        return publicationService.findByTitle(title);
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return publicationService.getCategories();
    }

    @PostMapping("/add")
    public Publication addPublication(@RequestBody PublicationDto publication) {
        return publicationService.createPublication(publication);
    }

    @GetMapping("/find/user/{id}")
    public List<Publication> findPublicationByUserId(@PathVariable Long id) {
        return publicationService.findByUserId(id);
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PublicationDto findPublicationInfoById(@PathVariable Long id) {
        return publicationService.findById(id);
    }

    @PostMapping(value = "/{id}/upload-image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String uploadFile(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) throws IOException {
        publicationService.addImageToPublication(id, file);

        return "huy";
    }
}
