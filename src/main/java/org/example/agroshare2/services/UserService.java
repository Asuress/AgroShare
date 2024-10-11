package org.example.agroshare2.services;

import lombok.RequiredArgsConstructor;
import org.example.agroshare2.dto.PersonDto;
import org.example.agroshare2.entities.Individual;
import org.example.agroshare2.entities.Legal;
import org.example.agroshare2.entities.Role;
import org.example.agroshare2.entities.User;
import org.example.agroshare2.repositories.IndividualRepository;
import org.example.agroshare2.repositories.LegalRepository;
import org.example.agroshare2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    @Autowired
    private IndividualRepository individualRepository;

    @Autowired
    private LegalRepository legalRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public User save(User user) {
        return repository.save(user);
    }


    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(user);
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }


    /**
     * Выдача прав администратора текущему пользователю
     * <p>
     * Нужен для демонстрации
     */
    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }

    public PersonDto getUserById(Long id) {
        return entityToView(repository.findById(id).orElseThrow());
    }

    private PersonDto entityToView(User user) {
        PersonDto person = PersonDto.builder()
                .userType(user.getPersonType())
                .username(user.getUsername())
                .email(user.getEmail())
                .inn(user.getInn())
                .phoneNumber(user.getPhoneNumber())
                .image(user.getImage())
                .build();

        if ("I".equals(user.getPersonType()) && user.getTypedUserId() != null) {
            Optional<Individual> individual = individualRepository.findById(user.getTypedUserId());
            if (individual.isPresent()) {
                person.setFirstName(individual.get().getFirstName());
                person.setLastName(individual.get().getLastName());
            }
        } else if ("L".equals(user.getPersonType()) && user.getTypedUserId() != null) {
            Optional<Legal> legal = legalRepository.findById(user.getTypedUserId());
            if (legal.isPresent()) {
                person.setOrganizationName(legal.get().getOrganizationName());
            }
        }

        return person;
    }

    public void addImageToProfile(Long id, MultipartFile file) {
        Optional<User> userById = userRepository.findById(id);
        User user = userById.orElseThrow();
        Resource resource = file.getResource();
        try {
            user.setImage(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        userRepository.save(user);
    }
}
