package org.example.agroshare2.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.agroshare2.entities.Category;

@Getter
@Setter
public class PublicationDto {
    private String title;
    private String description;
    private String price;
    private String publicationType;
    private String category;
    private String publisher;
    private byte[] image;
}
