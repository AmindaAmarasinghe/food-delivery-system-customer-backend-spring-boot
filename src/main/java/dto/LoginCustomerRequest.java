package dto;

import lombok.Data;

@Data
public class LoginCustomerRequest {
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
}
