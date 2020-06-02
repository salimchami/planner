package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.infrastructure.temp.ClientDTO;
import io.edukativ.myskoolin.infrastructure.temp.ClientMapper;
import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.commercial.dto.ClientDbDTO;
import io.edukativ.myskoolin.infrastructure.schooling.repository.ClientRepository;
import org.bson.types.ObjectId;
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
        Optional<UserDbDTO> user = userService.getCurrentUserWithAuthorities();
        if (user.isPresent()) {
            ObjectId clientId = user.get().getClientId();
            Optional<ClientDbDTO> client = clientRepository.findOneById(clientId);
            if (client.isPresent()) {
                return Optional.of(clientMapper.dbDTOtoDTO(client.get()));
            }
        }
        return Optional.empty();
    }
}
