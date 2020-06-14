package io.edukativ.myskoolin.infrastructure.teachers;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/**
 * Spring Data MongoDB fr.wideapps.schoolme.repository for the User entity.
 */
@Repository
public interface TeacherRepository extends MongoRepository<TeacherDbDTO, String> {

    @Query("{ 'deleted': false, 'clientId': ?0 }")
    List<TeacherDbDTO> findAllNotDeletedTeachers(ObjectId clientId);

    @Query("{ 'clientId': ?0, 'teacher' : {'$ne' : null} }")
    List<TeacherDbDTO> findAllTeachers(ObjectId clientId);

    @Query("{ 'deleted': false }")
    List<TeacherDbDTO> findAllNotDeletedTeachers();

    @Query("{ 'deleted': false, 'clientId': ?0, $text: { $search: ?1 } }")
    List<TeacherDbDTO> searchTeachers(ObjectId clientId, String search);

    @Query("{ 'deleted': false, 'clientId': ?0, $text: { $search: ?1 } }")
    List<TeacherDbDTO> searchStudents(ObjectId clientId, String search);

    @Query("{ $text: { $search: ?1 } }")
    List<TeacherDbDTO> searchStudents(String search);

    List<TeacherDbDTO> findAllByDeletedIsTrueAndCreatedDateBefore(Instant minus);

    @Query("{ '_id': {$in: ?0} }")
    List<TeacherDbDTO> findByIds(List<ObjectId> collect);

    @Query("{ 'deleted': false, 'clientId': ?0, 'student.schoolClassId': ?1 }")
    List<TeacherDbDTO> findStudentsBySchoolClassId(ObjectId clientId, String schoolClassId);

    @Query("{ $text: { $search: ?1 } }")
    List<TeacherDbDTO> searchTeachers(String search);

    @Query("{ 'deleted': false, 'clientId': ?0, 'teacher.grades.$id': {$in: ?1},  'taughtSubjects': {$in: ?2}}")
    List<TeacherDbDTO> findNotDeletedTeachersByGradesIdsAndSubjectsId(ObjectId clientId, List<String> gradesIds, List<String> subjectsIds);

    @Query("{ 'deleted': false, 'clientId': ?0, 'teacher.grades.$id': ?1 }")
    List<TeacherDbDTO> findNotDeletedTeachersByGradeId(ObjectId clientId, ObjectId gradeId);

}
