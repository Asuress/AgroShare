package org.example.agroshare2.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "public", name = "legal")
public class Legal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "individual_id")
    private Long id;

    @Column(name = "legal_name")
    private String organizationName;
}
