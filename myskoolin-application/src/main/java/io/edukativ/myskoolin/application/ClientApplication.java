package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.application.security.CurrentUserNotFoundException;
import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.infrastructure.app.dto.UserDbDTO;
import io.edukativ.myskoolin.infrastructure.commercial.ClientDTO;
import io.edukativ.myskoolin.infrastructure.commercial.ClientDbDTO;
import io.edukativ.myskoolin.infrastructure.commercial.ClientMapper;
import io.edukativ.myskoolin.infrastructure.commercial.ClientRepository;
import io.edukativ.myskoolin.infrastructure.timetabling.TimeTableOptionsMapper;
import io.edukativ.myskoolin.infrastructure.timetabling.TimeTableOptionsVO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
public class ClientApplication {

    private final UserService userService;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final TimeTableOptionsMapper timeTableOptionsMapper;

    public ClientApplication(UserService userService, ClientRepository clientRepository, ClientMapper clientMapper,
                             TimeTableOptionsMapper timeTableOptionsMapper) {
        this.userService = userService;
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.timeTableOptionsMapper = timeTableOptionsMapper;
    }

    public Optional<ClientDTO> currentClient() {
        try {
            UserDbDTO user = userService.currentUserWithAuthorities();
            Optional<ClientDbDTO> client = clientRepository.findOneById(user.getClientId());
            return Optional.of(clientMapper.map(client.get()));
        } catch (CurrentUserNotFoundException e) {
            return Optional.empty();
        }
    }

    public Optional<TimeTableOptionsVO> timeTableOptions() {
        UserDbDTO user = userService.currentUserWithAuthorities();
        final Optional<ClientDbDTO> optClient = clientRepository.findOneById(user.getClientId());
        return optClient.map(client -> timeTableOptionsMapper.dbVoToVo(client.getTimeTableOptions()));
    }
}
