package uz.example.apprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.example.apprelationships.entity.Faculty;
import uz.example.apprelationships.entity.Subject;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    boolean existsByNameAndUniversityId(String name, Integer university_id);

    List<Faculty> findAllByUniversityId(Integer university_id);
}
