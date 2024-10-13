package org.example.agroshare2.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.agroshare2.dto.JwtAuthenticationResponse;
import org.example.agroshare2.dto.SignInRequest;
import org.example.agroshare2.dto.SignUpRequest;
import org.example.agroshare2.dto.UserDTO;
import org.example.agroshare2.services.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody SignInRequest request) {
        return authenticationService.signIn(request);
    }

    @PostMapping("/register")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }
}
