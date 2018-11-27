package com.scerp.ecommercesystem.repository;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import com.scerp.ecommercesystem.domain.CustomerEntity;
public interface CustomerRepository extends CrudRepository <CustomerEntity,Long> {
	
	@Modifying
	@Transactional
	void modifyByCustomerId(int customer_id);
	CustomerEntity findByCustomerEmail(String email); 

}
