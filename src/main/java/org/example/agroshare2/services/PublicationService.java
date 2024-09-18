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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

    List<Publication> publications;
    @Autowired
    private UserRepository userRepository;

    public List<Publication> getLastPublications() {
//        publications = new ArrayList<>();
//        for (int i = 0; i < 7; i++) {
//            Publication publication = new Publication();
//            publication.setTitle("Title " + i);
//            publications.add(publication);
//        }
        return publicationRepository.findLast10();
    }

    public List<Publication> findByTitle(String title) {

        return publications.stream()
                .filter(publication ->
                {
                    Pattern pattern = Pattern.compile(".*"
                                    + title.replace(" ", ".*")
                                    + ".*",
                            Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(publication.getTitle()
                            .toLowerCase()
                            .strip());
                    return matcher.find();
                })
                .collect(Collectors.toList());
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

            Optional<Category> categoryById = categoryRepository.findById(publication.getCategory());
            publicationDto.setCategory(categoryById.orElseThrow().getCategoryName());

            Optional<User> userById = userRepository.findById(publication.getUserId());
            String email = userById.orElseThrow().getEmail();

            publicationDto.setPublisher(email);
            return publicationDto;
        }
        return null;
    }
}
