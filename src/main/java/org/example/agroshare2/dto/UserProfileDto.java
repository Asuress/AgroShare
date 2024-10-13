package org.example.agroshare2.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.example.agroshare2.entities.Role;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {
    private long id;
    private String personType;
    private String username;

    private String organizationName;

    private String firstName;
    private String lastName;
    private String middleName;

    private String email;
    private String phoneNumber;
    private String location;
    private String inn;
    private byte[] image;
}
