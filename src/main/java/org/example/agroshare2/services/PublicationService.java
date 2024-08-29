package org.example.agroshare2.services;

import org.apache.commons.lang3.RegExUtils;
import org.example.agroshare2.entities.Publication;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PublicationService {
    List<Publication> publications;

    public List<Publication> getLastPublications() {
        publications = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Publication publication = new Publication();
            publication.setTitle("Title " + i);
            publications.add(publication);
        }
        return publications;
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
}
