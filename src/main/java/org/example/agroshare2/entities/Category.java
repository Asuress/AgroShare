package org.example.agroshare2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "categories")
@SequenceGenerator(name = "public.categories_s", sequenceName = "categories_s", schema = "public")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_s")
    @SequenceGenerator(name = "categories_s", sequenceName = "categories_s", allocationSize = 1)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "title")
    private String categoryName;

}
