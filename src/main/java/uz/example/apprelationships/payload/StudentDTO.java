package uz.example.apprelationships.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.example.apprelationships.entity.Subject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private String firstname;
    private String lastname;
    private String city;
    private String district;
    private String street;
    private Subject subject;
    private Integer classId;
}

