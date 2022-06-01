package uz.example.apprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.example.apprelationships.entity.Address;
import uz.example.apprelationships.entity.Classes;

import java.util.List;

public interface ClassesRepository extends JpaRepository<Classes, Integer> {

    List<Classes> findAllByFaculty_University_Id(Integer faculty_university_id);

//    @Query("select gr from classes gr where gr.faculty.universty.id=:universityId")
//    List<Classes> getClassByUniversityId(Integer universityId);

    @Query(value = "select *\n" +
            "from classes c\n" +
            "         join faculty f on f.id = c.faculty_id\n" +
            "         join university u on u.id = f.university_id where u.id=:universityId", nativeQuery = true)
    List<Classes> getClassByUniversityIdNative(Integer universityId);
}
