package customer.repository;

import customer.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	@Query(nativeQuery = true, value = "SELECT customer_id, fname, lname, email, password, contact, longitude, latitude FROM customer WHERE email=:email")
	public Customer findByEmail(@Param("email") String email);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE customer SET password=:password WHERE email=:email")
	public void setPassword(@Param("email") String email,@Param("password") String password);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE customer SET fname=:fname, lname=:lname, contact=:contact WHERE email=:email")
	public void editCustomer(@Param("fname") String fname,@Param("lname") String lname,@Param("contact") String city, @Param("email") String email);

	@Query(nativeQuery = true, value = "SELECT * FROM customer WHERE customer_id=:customer_id")
	public Customer findByCustomerID(@Param("customer_id") int customer_id);
}