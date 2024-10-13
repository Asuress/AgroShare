package org.example.agroshare2.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.agroshare2.entities.Category;

@Getter
@Setter
public class PublicationDto {
    private Long id;
    private String title;
    private String description;
    private String price;
    private String publicationType;
    private String category;
    private String publisherEmail;
    private Long publisherId;
    private String image;
}
