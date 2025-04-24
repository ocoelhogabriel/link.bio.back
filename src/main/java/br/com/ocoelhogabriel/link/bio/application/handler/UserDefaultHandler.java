package br.com.ocoelhogabriel.link.bio.application.handler;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import br.com.ocoelhogabriel.link.bio.domain.entity.Access;
import br.com.ocoelhogabriel.link.bio.domain.entity.User;
import br.com.ocoelhogabriel.link.bio.domain.entity.repository.AccessRepository;
import br.com.ocoelhogabriel.link.bio.domain.entity.repository.UserRepository;
import jakarta.annotation.PostConstruct;

@Configuration
public class UserDefaultHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserDefaultHandler.class);
    private static final String USER_DEFAULT_NOT_CREATED = "User default not created";
    private static final String ACCESS_DEFAULT_NOT_CREATED = "Access default not created";

    private final UserRepository userRepository;
    private final AccessRepository accessRepository;

    public UserDefaultHandler(UserRepository userRepository, AccessRepository accessRepository) {
        super();
        this.userRepository = userRepository;
        this.accessRepository = accessRepository;
    }

    @PostConstruct
    private void handlerUserDefault() {
        Optional<Access> accessDefault = accessRepository.findByLogin("admin");
        if (accessDefault.isEmpty()) {

            UUID userId = createUserDefault();
            createAccessDefault(userId);
            return;
        }

        logger.info("User default already created");
        User user = updateUserDefault(accessDefault.get().getUserId());
        updateAccessDefault(accessDefault.get(), user.getId());
    }

    private UUID createUserDefault() {
        try {
            logger.info("Creating user default");
            User userDefault = User.createUserDefault();
            userDefault = userRepository.save(userDefault);

            if (userDefault.getId() == null) {
                throw new RuntimeException(USER_DEFAULT_NOT_CREATED);
            }
            logger.info("User default created with id: {}", userDefault.getId());
            return userDefault.getId();
        } catch (Exception e) {
            throw new RuntimeException(USER_DEFAULT_NOT_CREATED, e);
        }
    }

    private void createAccessDefault(UUID userId) {
        try {
            logger.info("Creating access default for user with id: {}", userId);
            Access newAccessDefault = Access.createAccessDefault(userId);
            newAccessDefault = accessRepository.save(newAccessDefault);

            if (newAccessDefault.getId() == null) {
                throw new RuntimeException(ACCESS_DEFAULT_NOT_CREATED);
            }
            logger.info("Access default created with id: {}", newAccessDefault.getId());
        } catch (Exception e) {
            throw new RuntimeException(ACCESS_DEFAULT_NOT_CREATED, e);
        }
    }

    private User updateUserDefault(UUID userId) {
        try {
            logger.info("Creating user default");
            User userDefault = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(USER_DEFAULT_NOT_CREATED));
            User updateUuserDefault = userRepository.save(User.updateUserDefault(userDefault));

            if (updateUuserDefault.getId() == null) {
                throw new RuntimeException(USER_DEFAULT_NOT_CREATED);
            }
            logger.info("User default created with id: {}", updateUuserDefault.getId());
            return updateUuserDefault;
        } catch (Exception e) {
            throw new RuntimeException(USER_DEFAULT_NOT_CREATED, e);
        }
    }

    private void updateAccessDefault(Access accessDefault, UUID userId) {
        try {
            logger.info("Updating access default for user with id: {}", userId);
            Access updateAccessDefault = accessRepository.save(Access.updateAccessDefault(accessDefault, userId));

            if (updateAccessDefault.getId() == null) {
                throw new RuntimeException(ACCESS_DEFAULT_NOT_CREATED);
            }
            logger.info("Access default update with id: {}", updateAccessDefault.getId());
        } catch (Exception e) {
            throw new RuntimeException(ACCESS_DEFAULT_NOT_CREATED, e);
        }
    }
}
