package uz.example.apprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.example.apprelationships.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
}
