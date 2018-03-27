package com.mvetchinov.arrears.repositories;

import com.mvetchinov.arrears.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
