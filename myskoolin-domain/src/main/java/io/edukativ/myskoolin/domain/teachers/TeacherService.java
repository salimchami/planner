package io.edukativ.myskoolin.domain.teachers;

import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.domain.commons.AuthoritySPI;
import io.edukativ.myskoolin.domain.commons.MyskoolinLoggerSPI;
import io.edukativ.myskoolin.domain.commons.mailing.MyskoolinMailingSPI;
import io.edukativ.myskoolin.domain.commons.entity.Authority;
import io.edukativ.myskoolin.domain.commons.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TeacherService implements TeacherAPI {

    private final TeacherSPI teacherSPI;
    private final AuthoritySPI authoritySPI;
    private final TeacherMailingSPI teacherMailingSPI;
    private final MyskoolinMailingSPI myskoolinMailingSPI;
    private final MyskoolinLoggerSPI logger;

    public TeacherService(TeacherSPI teacherSPI, AuthoritySPI authoritySPI, TeacherMailingSPI teacherMailingSPI,
                          MyskoolinMailingSPI myskoolinMailingSPI, MyskoolinLoggerSPI logger) {
        this.teacherSPI = teacherSPI;
        this.authoritySPI = authoritySPI;
        this.teacherMailingSPI = teacherMailingSPI;
        this.myskoolinMailingSPI = myskoolinMailingSPI;
        this.logger = logger;
    }

    @Override
    public Teacher create(Teacher teacher, String encryptedRandomPassword, User currentUser, String baseUrl) {
        final Authority authority = authoritySPI.findByName(AuthoritiesConstants.TEACHERS);
        if (!teacher.containsAuthority(authority)) {
            teacher.addAuthority(authority);
        }
        teacher.setPassword(encryptedRandomPassword);
        teacher.setDeleted(false);
        teacher.setArchived(false);
        teacher.setActivated(false);
        teacher.setClientId(currentUser.getClientId());
        teacher.setCreatedBy(currentUser.getId());
        Teacher createdTeacher = teacherSPI.create(teacher);
        teacherMailingSPI.sendToCreatedTeacher(createdTeacher, baseUrl);
        myskoolinMailingSPI.notifyCreatedTeacher(createdTeacher);
        return createdTeacher;
    }

    @Override
    public List<Teacher> searchByName(String name, User currentUser) {
        if (currentUser.hasAuthority(AuthoritiesConstants.SCHOOLME_ADMIN)) {
            return teacherSPI.searchTeachers(name);
        } else {
            return teacherSPI.searchTeachers(currentUser.getClientId(), name);
        }
    }

    @Override
    public Optional<Teacher> update(Teacher teacher, User currentUser, String baseUrl) {
        return Optional.of(teacherSPI.findById(teacher.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(dbTeacher -> {
                    Set<Authority> managedAuthorities = teacher.getAuthorities();
                    managedAuthorities.clear();
                    teacher.getAuthorities().stream()
                            .map(authority -> authoritySPI.findById(authority.getName()))
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .forEach(managedAuthorities::add);
                    authoritySPI.findById(AuthoritiesConstants.TEACHERS)
                            .ifPresent(teacher::checkTeacherAuthority);
                    dbTeacher.setAddress(teacher.getAddress());
                    dbTeacher.setBirthDate(teacher.getBirthDate());
                    dbTeacher.setCellPhone(teacher.getCellPhone());
                    dbTeacher.setFirstName(teacher.getFirstName());
                    dbTeacher.setHomePhone(teacher.getHomePhone());
                    dbTeacher.setLangKey(teacher.getLangKey());
                    dbTeacher.setLastName(teacher.getLastName());
                    dbTeacher.setNationality(teacher.getNationality());
                    dbTeacher.setGender(teacher.getGender());
                    dbTeacher.setComment(teacher.getComment());
                    dbTeacher.setEmployedDate(teacher.getEmployedDate());
                    dbTeacher.setFamilySituation(teacher.getFamilySituation());
                    dbTeacher.setSubstitute(teacher.getSubstitute());
                    dbTeacher.setSubstitutedTeachers(teacher.getSubstitutedTeachers());
                    dbTeacher.setTaughtSubjects(teacher.getTaughtSubjects());
                    dbTeacher.setProCellPhone(teacher.getProCellPhone());
                    dbTeacher.setProPhone(teacher.getProPhone());
                    dbTeacher.setProEmail(teacher.getProEmail());
                    dbTeacher.setMedicalInfos(teacher.getMedicalInfos());
                    dbTeacher.setExitDate(teacher.getExitDate());
                    dbTeacher.setExitReason(teacher.getExitReason());
                    dbTeacher.setGrades(teacher.getGrades());
                    logger.debug(String.format("Changed Information for User: %s", dbTeacher));
                    return dbTeacher;
                });
    }
}
