package org.example.agroshare2.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String username;
    private String inn;
    private String userType;
    private String organizationName;
}
