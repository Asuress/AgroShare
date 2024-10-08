package org.example.agroshare2.services;

import org.apache.commons.lang3.RegExUtils;
import org.example.agroshare2.dto.PublicationDto;
import org.example.agroshare2.entities.Category;
import org.example.agroshare2.entities.Publication;
import org.example.agroshare2.entities.User;
import org.example.agroshare2.repositories.CategoryRepository;
import org.example.agroshare2.repositories.PublicationRepository;
import org.example.agroshare2.repositories.UserRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PublicationService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Publication> getLastPublications() {
        return publicationRepository.findLast10();
    }

    public List<Publication> findByTitle(String title) {
        return publicationRepository.findByTitleLike('%' + title + '%');
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Publication createPublication(PublicationDto publicationDto) {
        Publication publication = new Publication();

        Optional<User> byUsername = userRepository.findByUsername(publicationDto.getPublisher());

        publication.setTitle(publicationDto.getTitle());
        publication.setUserId(byUsername.isPresent() ? byUsername.get().getId() : null);
        publication.setPrice(publicationDto.getPrice());
        publication.setPublicationType(publicationDto.getPublicationType());
        publication.setDescription(publicationDto.getDescription());

        Optional<Category> category = categoryRepository.findByCategoryName(publicationDto.getCategory());
        publication.setCategory(category.isPresent() ? category.get().getCategoryId() : null);

        publicationRepository.save(publication);

        return publication;
    }

    public List<Publication> findByUserId(Long id) {
        return publicationRepository.findByUserId(id);
    }

    public PublicationDto findById(Long id) {
        Optional<Publication> byId = publicationRepository.findById(id);
        if (byId.isPresent()) {
            Publication publication = byId.get();
            PublicationDto publicationDto = new PublicationDto();
            publicationDto.setPublicationType(publication.getPublicationType());
            publicationDto.setDescription(publication.getDescription());
            publicationDto.setPrice(publication.getPrice());
            publicationDto.setTitle(publication.getTitle());
            publicationDto.setImage(publication.getImage());

            Optional<Category> categoryById = categoryRepository.findById(publication.getCategory());
            publicationDto.setCategory(categoryById.orElseThrow().getCategoryName());

            Optional<User> userById = userRepository.findById(publication.getUserId());
            String email = userById.orElseThrow().getEmail();

            publicationDto.setPublisher(email);
            return publicationDto;
        }
        return null;
    }

    public void addImageToPublication(Long id, MultipartFile file) throws IOException {
        Publication publication = publicationRepository.findById(id).orElseThrow();
        Resource resource = file.getResource();
        publication.setImage(file.getBytes());
        publicationRepository.save(publication);
    }
}
