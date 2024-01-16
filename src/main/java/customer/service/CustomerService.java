package customer.service;

import java.util.Date;

import customer.entity.Order;
import customer.entity.OrderedItem;
import customer.repository.OrderRepository;
import customer.repository.OrderedItemRepository;
import dto.EditRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
//import org.mindrot.jbcrypt.Bcrypt;


import customer.entity.Customer;
import customer.repository.CustomerRepository;

@Service
public class CustomerService {
	
	private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderedItemRepository orderedItemRepository;
	private static final Logger logger = LogManager.getLogger(CustomerRepository.class);
	
	//@Autowired
    //PasswordEncoder passwordEncoder;
    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository,OrderedItemRepository orderedItemRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.orderedItemRepository = orderedItemRepository;
    }
    public Map<String, String> generateOTP () {
    	Date date = new Date();
    	int randomNumber = ( int )( Math.random() * 9999 );

    	if( randomNumber <= 1000 ) 
    	    randomNumber = randomNumber + 1000;
        HashMap<String, String> map = new HashMap<>();
        map.put("is_successful", "true");
        map.put("parameter_errors", "null");
        map.put("server_datetime", String.valueOf(date));
        map.put("code", String.valueOf(randomNumber));
        return map;
    }
    public Iterable<Customer> getCustomers(){
        return customerRepository.findAll();
    }
    public Order findByOrderID(int order_id){
        return this.orderRepository.findByOrderID(order_id);
    }
    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    public void editCustomer(EditRequest editRequest){
        customerRepository.editCustomer(editRequest.getFname(), editRequest.getLname(), editRequest.getContact(), editRequest.getEmail());

    }
    public Customer getCustomer(String email){
        return customerRepository.findByEmail(email);
    }
    public Customer login(String email, String password) {
        Customer customer=customerRepository.findByEmail(email);
    	if(customer.getPassword().equals(password)) {
    		return customer;
    	}
    	return null;
    }
    public void resetPassword(String email, String password) {
    	logger.debug("....");
    	logger.info(email, password);
    	customerRepository.setPassword(email, password);
    }
    public Order saveOrder(Order order){
        return this.orderRepository.save(order);
    }

    public OrderedItem saveOrderedItem(OrderedItem orderedItem){
        return this.orderedItemRepository.save(orderedItem);
    }
    public Customer findByCustomerID(int customer_id){
        return this.customerRepository.findByCustomerID(customer_id);
    }


}
