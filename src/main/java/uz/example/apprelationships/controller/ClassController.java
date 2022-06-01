package uz.example.apprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.example.apprelationships.entity.Classes;
import uz.example.apprelationships.entity.Faculty;
import uz.example.apprelationships.payload.ClassDTO;
import uz.example.apprelationships.repository.ClassesRepository;
import uz.example.apprelationships.repository.FacultyRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Classes")
public class ClassController {

    @Autowired
    ClassesRepository classesRepository;
    @Autowired
    FacultyRepository facultyRepository;

    //VAZIRLIK u.n
    //READ
    @GetMapping
    public List<Classes> getAllClasses() {
        return classesRepository.findAll();
    }

    //Universitet ma'sul xodimi u.n
    @GetMapping("/byUniversityId/{universityId}")
    public List<Classes> getClassesByUniversityId(@PathVariable Integer universityId) {

        List<Classes> allByFaculty_university_id = classesRepository.findAllByFaculty_University_Id(universityId);

        List<Classes> classByUniversityIdNative = classesRepository.getClassByUniversityIdNative(universityId);

        return allByFaculty_university_id;
    }


    @PostMapping
    public String addClass(@RequestBody ClassDTO classDTO) {
        Classes classes = new Classes();
        classes.setName(classDTO.getName());
        Optional<Faculty> byId = facultyRepository.findById(classDTO.getFacultyId());

        if (!byId.isPresent())
            return "such faculty not found";

        classes.setFaculty(byId.get());

        classesRepository.save(classes);
        return "Class added";
    }
}
