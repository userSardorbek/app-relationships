package uz.example.apprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.example.apprelationships.entity.Faculty;
import uz.example.apprelationships.entity.University;
import uz.example.apprelationships.payload.FacultyDTO;
import uz.example.apprelationships.repository.FacultyRepository;
import uz.example.apprelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    UniversityRepository universityRepository;

    @PostMapping
    public String addFaculty(@RequestBody FacultyDTO facultyDTO) {

        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyDTO.getFacultyName(), facultyDTO.getUniversityId());
        if (exists)
            return "Such faculty is already exist in this university";

        Faculty faculty = new Faculty();

        Optional<University> byId = universityRepository.findById(facultyDTO.getUniversityId());
        if (!byId.isPresent())

            return "university not exist";

        faculty.setName(facultyDTO.getFacultyName());
        faculty.setUniversity(byId.get());

        facultyRepository.save(faculty);
        return "faculty saved";
    }


    //Vazirlik u.n
    @GetMapping
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    //Universitet u.n
    @GetMapping("/byUniversityId/{universityId}")
    public List<Faculty> getFacultiesByUniversityId(@PathVariable Integer universityId) {

        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(universityId);
        return allByUniversityId;
    }

    //UPDATE
    @PutMapping("/{facultyId}")
    public String editFaculty(@RequestBody FacultyDTO facultyDTO, @PathVariable Integer facultyId){

        Optional<Faculty> byId = facultyRepository.findById(facultyId);
        if (!byId.isPresent())
            return "Faculty not found";

        Faculty faculty = byId.get();
        faculty.setName(facultyDTO.getFacultyName());

        facultyRepository.save(faculty);

        return "Successfully edited";
    }


    //DELETE
    @DeleteMapping("/{facultyId}")
    public String deleteFaculty(@PathVariable Integer facultyId){
        Optional<Faculty> byId = facultyRepository.findById(facultyId);
        if (!byId.isPresent())
            return "Faculty not found";
        facultyRepository.deleteById(facultyId);
        return "Faculty deleted";
    }

}
