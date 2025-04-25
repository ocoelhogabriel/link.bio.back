package br.com.ocoelhogabriel.link.bio.application.handler;

import java.math.BigInteger;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import br.com.ocoelhogabriel.link.bio.application.services.AccessService;
import br.com.ocoelhogabriel.link.bio.application.services.UserService;
import br.com.ocoelhogabriel.link.bio.domain.entity.Access;
import br.com.ocoelhogabriel.link.bio.domain.entity.User;
import jakarta.annotation.PostConstruct;

@Configuration
public class UserDefaultHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserDefaultHandler.class);

    private final UserService userService;
    private final AccessService accessService;

    public UserDefaultHandler(UserService userService, AccessService accessService) {
        super();
        this.userService = userService;
        this.accessService = accessService;
    }

    @PostConstruct
    private void handlerUserDefault() {
        Optional<Access> accessDefault = accessService.findByLogin("admin");
        if (accessDefault.isEmpty()) {

            BigInteger userId = userService.createUserDefault();
            accessService.createAccessDefault(userId);
            return;
        }

        logger.info("User default already created");
        User user = userService.updateUserDefault(accessDefault.get().getUserId());
        accessService.updateAccessDefault(accessDefault.get(), user.getId());
    }

}
