package customer.controller;

import java.util.HashMap;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dto.*;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.*;
import customer.entity.Customer;
import customer.service.*;

@RestController
@RequestMapping(value="/api/v1")
public class CustomerController {

	
	private final CustomerService customerService;
	private final MailService mailService;
	private final KafkaSender kafkaSender;
	private final KafkaConsumer kafkaConsumer;

	public CustomerController(CustomerService customerService,MailService mailService,KafkaSender kafkaSender,KafkaConsumer kafkaConsumer) {
		this.customerService = customerService;
		this.mailService = mailService;
		this.kafkaSender = kafkaSender;
		this.kafkaConsumer = kafkaConsumer;
	}

	@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/customers")
    public Iterable<Customer> findAllCustomers() {
        return this.customerService.getCustomers();    
    }


	@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/reset_password_otp")
	@ResponseBody
    public Map<String, String> generateOTPemail(@RequestBody OTPRequest otpReq) {
		HashMap<String, String> map = (HashMap<String, String>) this.customerService.generateOTP();
		//generate email with unhashed OTP
		String mailBody = "Your OTP is "+ map.get("code")+ ". please use this number to continue on resetting the password";
		this.mailService.sendSimpleMessage(otpReq.getEmail(), "Verification code", mailBody);
		map.put("code", BCrypt.hashpw(map.get("code"), "$2a$10$CwTycUXWue0Thq9StjUM0u")); //hashed OTP
		return map;
    }

	@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/password_reset")
	@ResponseBody
    public void resetPassword(@RequestBody PasswordReset passwordReset) {
		this.customerService.resetPassword(passwordReset.getEmail(), passwordReset.getPassword());
    }
	
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value="/setCustomer")
    public Customer addOneCustomer(@RequestBody Customer customer) {
        return this.customerService.saveCustomer(customer);
    }

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(value="/editCustomer")
	public void editCustomer(@RequestBody EditRequest editRequest) {
		this.customerService.editCustomer(editRequest);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value="/getCustomer")
	public Customer getCustomer(@RequestParam("email") String email) {
		return this.customerService.getCustomer(email);
	}

	@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    @ResponseBody
    public Object login(@RequestBody LoginCustomerRequest body) {
    	String email = body.getEmail();
    	String password = body.getPassword();
    	Map<String, Object> object = new HashMap<>();
	    Customer customer = this.customerService.login(email,password);
    	if(customer!=null) {
    		object.put("key", "success");
			object.put("customer_id", customer.getCustomer_id());
			object.put("username", customer.getFname()+' '+ customer.getLname());
    	}else {
    		object.put("key", "failure");
    	}
    	return object;
    }

	@PostMapping(value = "/publish")
	public String producer(@RequestParam("topic") String topic,@RequestParam("message") String message) {
		this.kafkaSender.send(topic,message);

		return "Message sent to the Kafka Topic Successfully.\n";
	}



}
