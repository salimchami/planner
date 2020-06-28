package io.edukativ.myskoolin.infrastructure.students;

import io.edukativ.myskoolin.domain.students.Student;
import io.edukativ.myskoolin.infrastructure.app.mapper.AuthorityMapper;
import io.edukativ.myskoolin.infrastructure.common.mapper.AddressMapper;
import io.edukativ.myskoolin.infrastructure.common.mapper.ObjectIdMapper;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class StudentMapperImplemented {

    @Autowired
    private ObjectIdMapper objectIdMapper;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private AddressMapper addressMapper;

    public StudentDbDTO domainToDbDto(Student student) {

        if (student == null) {
            return null;
        }

        StudentDbDTO.StudentDbDTOBuilder studentBuilder = new StudentDbDTO.StudentDbDTOBuilder();

        return studentBuilder
                .id(student.getId())
                .login(student.getLogin())
                .clientId(objectIdMapper.map(student.getClientId()))
                .password(student.getPassword())
                .gender(student.getGender())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .cellPhone(student.getCellPhone())
                .homePhone(student.getHomePhone())
                .nationality(student.getNationality())
                .email(student.getEmail())
                .activated(student.isActivated())
                .langKey(student.getLangKey())
                .imageUrl(student.getImageUrl())
                .activationKey(student.getActivationKey())
                .resetKey(student.getResetKey())
                .resetDate(student.getResetDate())
                .authorities(authorityMapper.domainsToDbDtos(student.getAuthorities()))
                .address(addressMapper.domainToDbDto(student.getAddress()))
                .birthDate(student.getBirthDate())
                .deleted(student.isDeleted())
                .archived(student.isArchived())
                .createdBy(student.getCreatedBy())

                .build();
    }
}
