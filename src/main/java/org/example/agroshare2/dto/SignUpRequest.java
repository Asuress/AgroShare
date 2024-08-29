package org.example.agroshare2.dto;

import lombok.Data;

@Data
public class SignUpRequest {

    private String username;

    private String email;

    private String password;
}
