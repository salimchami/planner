package io.edukativ.myskoolin.application;

import io.edukativ.myskoolin.application.security.UserService;
import io.edukativ.myskoolin.domain.commons.entity.User;
import io.edukativ.myskoolin.domain.schoolclasses.SchoolClassAPI;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassDTO;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassDbDTO;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassMapper;
import io.edukativ.myskoolin.infrastructure.schoolclasses.SchoolClassRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SchoolClassApplication {

    private final SchoolClassRepository schoolClassRepository;
    private final SchoolClassAPI schoolClassAPI;
    private final UserService userService;
    private final SchoolClassMapper schoolClassMapper;

    public SchoolClassApplication(SchoolClassRepository schoolClassRepository, SchoolClassAPI schoolClassAPI,
                                  UserService userService, SchoolClassMapper schoolClassMapper) {
        this.schoolClassRepository = schoolClassRepository;
        this.schoolClassAPI = schoolClassAPI;
        this.userService = userService;
        this.schoolClassMapper = schoolClassMapper;
    }

    public List<SchoolClassDTO> findAll() {
        final User currentUser = userService.currentUser();
        final ObjectId clientId = new ObjectId(currentUser.getClientId());
        final List<SchoolClassDbDTO> schoolClasses = schoolClassRepository.findAllNotDeletedSchoolClasses(clientId);
        return schoolClassMapper.dbDtosToDtos(schoolClasses);
    }

    public Optional<SchoolClassDTO> findOne(String id) {
        final User currentUser = userService.currentUser();
        final ObjectId clientId = new ObjectId(currentUser.getClientId());
        final Optional<SchoolClassDbDTO> optSchoolClass = schoolClassRepository.findOneByIdNotDeleted(clientId, new ObjectId(id));
        return optSchoolClass.map(schoolClassMapper::dbDtoToDto);
    }
}
