package io.edukativ.myskoolin.domain.teachers;

import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.edukativ.myskoolin.domain.commons.AuthoritySPI;
import io.edukativ.myskoolin.domain.commons.mailing.MyskoolinMailingSPI;
import io.edukativ.myskoolin.domain.entity.Authority;
import io.edukativ.myskoolin.domain.entity.User;
import io.edukativ.myskoolin.domain.vo.Teacher;

import java.util.List;

public class TeacherService implements TeacherAPI {

    private final TeacherSPI teacherSPI;
    private final AuthoritySPI authoritySPI;
    private final TeacherMailingSPI teacherMailingSPI;
    private final MyskoolinMailingSPI myskoolinMailingSPI;

    public TeacherService(TeacherSPI teacherSPI, AuthoritySPI authoritySPI, TeacherMailingSPI teacherMailingSPI,
                          MyskoolinMailingSPI myskoolinMailingSPI) {
        this.teacherSPI = teacherSPI;
        this.authoritySPI = authoritySPI;
        this.teacherMailingSPI = teacherMailingSPI;
        this.myskoolinMailingSPI = myskoolinMailingSPI;
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
}
