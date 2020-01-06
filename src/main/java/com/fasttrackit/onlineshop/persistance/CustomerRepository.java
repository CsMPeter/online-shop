package com.fasttrackit.onlineshop.persistance;

        import com.fasttrackit.onlineshop.domain.Customer;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
