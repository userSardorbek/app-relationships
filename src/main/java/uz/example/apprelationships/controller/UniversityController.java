package uz.example.apprelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ReactiveAdapter;
import org.springframework.web.bind.annotation.*;
import uz.example.apprelationships.entity.Address;
import uz.example.apprelationships.entity.University;
import uz.example.apprelationships.payload.UniversityDto;
import uz.example.apprelationships.repository.AddressRepository;
import uz.example.apprelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;


    //READ
    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getUniversity() {
        List<University> universities = universityRepository.findAll();
        return universities;
    }

    //CREATE
    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto) {
        Address address = new Address(
                universityDto.getCity(), universityDto.getDistrict(), universityDto.getStreet()
        );


        Address savedAddress = addressRepository.save(address);

        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(savedAddress);
        universityRepository.save(university);
        return "University added";
    }

    //Update
    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String editUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto) {
        Optional<University> byId = universityRepository.findById(id);
        if (byId.isPresent()) {

            University university = byId.get();
            university.setName(universityDto.getName());

            // University address
            Address address = university.getAddress();
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());

            addressRepository.save(address);

            universityRepository.save(university);

            return "University edited";
        }
        return "University not found";
    }

    //DELETE
    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    private String deleteUniversity(@PathVariable Integer id) {
        universityRepository.deleteById(id);
        return "university deleted";
    }
}
