package uz.example.apprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.example.apprelationships.entity.Subject;
import uz.example.apprelationships.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    //CREAT
    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestBody Subject subject) {
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName)
            return "This subject already exists";

        subjectRepository.save(subject);
        return "Subject added";
    }

    //READ
    @GetMapping
//    @RequestMapping(method = RequestMethod.GET) yuqoridagisi bilan bir xil manoda
    public List<Subject> getSubjects(){
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }

    //UPDATE
    @PutMapping(value = "/{id}")
    public String edit(@PathVariable Integer id ,@RequestBody Subject subject){
        Optional<Subject> byId = subjectRepository.findById(id);
        if (byId.isPresent()) {

            Subject aSubject = byId.get();
            aSubject.setName(subject.getName());

            subjectRepository.save(aSubject);
            return "subject edited";
        }
        return "subject not found";
    }

    //DELETE
    @RequestMapping(value = "/{id}")
    public String delete(@PathVariable Integer id){
        subjectRepository.deleteById(id);
        return "the subject is deleted";
    }
}
