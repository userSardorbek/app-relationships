package uz.example.apprelationships.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.example.apprelationships.entity.Address;
import uz.example.apprelationships.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> getStudentsByClasses_Faculty_University_Id(Integer universityId);

    Page<Student> findByClasses_Faculty_UniversityId(Integer classes_faculty_university_id, Pageable pageable);
}
