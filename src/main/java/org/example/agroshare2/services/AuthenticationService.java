package org.example.agroshare2.services;

import lombok.RequiredArgsConstructor;
import org.example.agroshare2.entities.Role;
import org.example.agroshare2.entities.User;
import org.example.agroshare2.dto.JwtAuthenticationResponse;
import org.example.agroshare2.dto.SignInRequest;
import org.example.agroshare2.dto.SignUpRequest;
import org.example.agroshare2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    public String getUsernameFromToken(String token) {

//        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        Jwt jwt = (Jwt) authenticationToken.getCredentials();
//        String email = (String) jwt.getClaims().get("email");
//        return email;
        return "";
    }

//    public boolean isTokenExpired(String token) {
//        return jwtService.isTokenValid(token);
//    }

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .personType(request.getPersonType())
                .role(Role.ROLE_USER)
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);

        Optional<User> usr = userRepository.findByUsername(user.getUsername());

        return new JwtAuthenticationResponse(jwt, usr.orElseThrow().getId());
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);

        Optional<User> usr = userRepository.findByUsername(user.getUsername());

        return new JwtAuthenticationResponse(jwt, usr.orElseThrow().getId());
    }
}
