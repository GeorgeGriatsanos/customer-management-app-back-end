package gr.aueb.cf.customermanagmentapplication.repository;

import gr.aueb.cf.customermanagmentapplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    void deleteCustomerById(Long id);
    Optional<Customer> findCustomerById(Long id);


    @Query(value = "SELECT * FROM customer WHERE user_id = :userId", nativeQuery = true)
    List<Customer> findAllByUserId(@Param("userId") Long userId);
}

