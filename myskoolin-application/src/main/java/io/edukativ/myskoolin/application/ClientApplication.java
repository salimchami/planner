package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.application.security.CurrentUserNotFoundException;
import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.commercial.ClientDTO;
import io.edukativ.myskoolin.infrastructure.commercial.ClientDbDTO;
import io.edukativ.myskoolin.infrastructure.commercial.ClientMapper;
import io.edukativ.myskoolin.infrastructure.commercial.ClientRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
public class ClientApplication {

    private final UserService userService;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientApplication(UserService userService, ClientRepository clientRepository, ClientMapper clientMapper) {
        this.userService = userService;
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public Optional<ClientDTO> currentClient() {
        try {
            UserDbDTO user = userService.getCurrentUserWithAuthorities();
            Optional<ClientDbDTO> client = clientRepository.findOneById(user.getClientId());
            return Optional.of(clientMapper.map(client.get()));
        } catch (CurrentUserNotFoundException e) {
            return Optional.empty();
        }
    }
}
