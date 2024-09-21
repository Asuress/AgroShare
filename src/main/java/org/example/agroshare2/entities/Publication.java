package org.example.agroshare2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.example.agroshare2.entities.Category;

@Getter
@Setter
@Entity
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "price")
    private String price;

    @Column(name = "publications_type")
    private String publicationType;

    @Column(name = "category")
//    @OneToOne
    private Long category;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

}
