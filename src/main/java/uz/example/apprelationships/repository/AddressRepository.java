package uz.example.apprelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.example.apprelationships.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
