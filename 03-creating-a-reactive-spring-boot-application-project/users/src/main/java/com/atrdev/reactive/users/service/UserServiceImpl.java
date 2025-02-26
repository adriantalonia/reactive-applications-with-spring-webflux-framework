package com.atrdev.reactive.users.service;

import com.atrdev.reactive.users.data.UserEntity;
import com.atrdev.reactive.users.data.UserRepository;
import com.atrdev.reactive.users.presentation.CreateUserRequest;
import com.atrdev.reactive.users.presentation.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service // Marks this class as a Spring service component, making it a candidate for dependency injection.
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository; // Dependency for interacting with the user data repository.

    // Constructor-based dependency injection for UserRepository.
    // This ensures that the UserRepository is provided when this service is instantiated.
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user based on the provided CreateUserRequest.
     *
     * @param createUserRequestMono A Mono containing the user creation request data.
     * @return A Mono containing the created user as a UserRest object.
     */
    @Override
    public Mono<UserRest> createUser(Mono<CreateUserRequest> createUserRequestMono) {
        // Step 1: Transform the CreateUserRequest into a UserEntity object.
        // mapNotNull ensures that null values are filtered out, preventing NullPointerException.
        return createUserRequestMono
                .mapNotNull(this::convertToEntity) // Synchronously map the request to a UserEntity, skipping null values.
                // Step 2: Save the UserEntity to the database asynchronously using flatMap.
                // userRepository::save is a method reference to save the entity asynchronously.
                .flatMap(userRepository::save) // Asynchronously save the entity and return a Mono<UserEntity>.
                // Step 3: Transform the saved UserEntity into a UserRest object.
                // mapNotNull ensures that null values are filtered out, preventing NullPointerException.
                .mapNotNull(this::convertToRest); // Synchronously map the saved entity to a UserRest, skipping null values.
    }

    @Override
    public Mono<UserRest> getUserById(UUID userId) {
        return userRepository.findById(userId).mapNotNull(this::convertToRest);
    }

    /**
     * Converts a CreateUserRequest object into a UserEntity object.
     *
     * @param createUserRequest The request object containing user data.
     * @return A UserEntity object populated with data from the request.
     */
    private UserEntity convertToEntity(CreateUserRequest createUserRequest) {
        UserEntity userEntity = new UserEntity(); // Create a new UserEntity instance.
        // Copy properties from the CreateUserRequest to the UserEntity using Spring's BeanUtils.
        // This avoids manual property mapping and reduces boilerplate code.
        BeanUtils.copyProperties(createUserRequest, userEntity);
        return userEntity; // Return the populated UserEntity.
    }

    /**
     * Converts a UserEntity object into a UserRest object.
     *
     * @param userEntity The entity object containing user data from the database.
     * @return A UserRest object populated with data from the entity.
     */
    private UserRest convertToRest(UserEntity userEntity) {
        UserRest userRest = new UserRest(); // Create a new UserRest instance.
        // Copy properties from the UserEntity to the UserRest using Spring's BeanUtils.
        // This avoids manual property mapping and reduces boilerplate code.
        BeanUtils.copyProperties(userEntity, userRest);
        return userRest; // Return the populated UserRest.
    }
}
