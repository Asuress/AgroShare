//package org.example.agroshare2.controllers;
//
//import org.example.agroshare2.repositories.PublicationRepository;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/file")
//public class FileController {
//    private final PublicationRepository publicationRepository;
//
//    public FileController(PublicationRepository publicationRepository) {
//        this.publicationRepository = publicationRepository;
//    }
//
//    @PostMapping(value = "/upload-file", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
//    public String uploadFile(@RequestParam("file") MultipartFile file) {
//        publicationRepository.findById()
//    }
//}
