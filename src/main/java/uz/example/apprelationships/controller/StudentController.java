package uz.example.apprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.example.apprelationships.entity.*;
import uz.example.apprelationships.payload.StudentDTO;
import uz.example.apprelationships.repository.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassesRepository classesRepository;

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SubjectRepository subjectRepository;

    //READ
    //For ministry
    @GetMapping("/forMinistry")
    public Page<Student> allStudentsForMinistry(@RequestParam int page) {

        //1-1=0  2-1=1  3-1=2
        //select * from student limit 10 offset (0*10)
        //select * from student limit 10 offset (1*10)
        //select * from student limit 10 offset (2*10)

        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }


    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> allStudentsForUniversity(@RequestParam int page, @PathVariable Integer universityId) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findByClasses_Faculty_UniversityId(universityId,pageable);
        return studentPage;
    }



    //READ
    //forUniversity
    @GetMapping("/{universityId}")
    public List<Student> universityStudents(@PathVariable Integer universityId) {
        return studentRepository.getStudentsByClasses_Faculty_University_Id(universityId);
    }

    //CREATE
    @PostMapping
    public String createStudent(@RequestBody StudentDTO studentDTO) {
        Address address = new Address(studentDTO.getCity(), studentDTO.getDistrict(), studentDTO.getStreet());

        Subject subject = new Subject();
        subject.setName(studentDTO.getSubject().getName());
        subjectRepository.save(subject);

        Optional<Classes> optionalClass = classesRepository.findById(studentDTO.getClassId());
        Classes classes = optionalClass.get();

        Student student = new Student();
        student.setAddress(address);
        student.setClasses(classes);
        student.setFirstName(studentDTO.getFirstname());
        student.setLastName(studentDTO.getLastname());

        subjectRepository.save(subject);
        return "New student saved";
    }
}
