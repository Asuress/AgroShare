package org.example.agroshare2.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "public", name = "individual")
public class Individual {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "individual_id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    @ColumnDefault("")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @ColumnDefault("")
    private String lastName;

    @Column(name = "second_name", nullable = false)
    @ColumnDefault("")
    private String middleName;

}
