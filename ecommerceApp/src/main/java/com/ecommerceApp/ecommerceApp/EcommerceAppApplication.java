package com.ecommerceApp.ecommerceApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@EnableAsync

public class EcommerceAppApplication {

	@Autowired
	private TokenStore tokenStore;

	@GetMapping("/doLogout")
	public String logout(HttpServletRequest request){
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null) {
			String tokenValue = authHeader.replace("Bearer", "").trim();
			OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
			tokenStore.removeAccessToken(accessToken);
		}
		return "Logged out successfully";
	}

	@GetMapping("/")
	public String index(){
		return "index";
	}

	@GetMapping("/admin/home")
	public String adminHome(){
		return "Admin Home";
	}

	@GetMapping("/users/home")
	public String userHome(){
		return "Users Home";
	}

	@GetMapping("/customer/home")
	public String customerHome(){
		return "Customer Home";
	}

	@GetMapping("/seller/home")
	public String sellerHome(){
		return "Seller Home";
	}


	public static void main(String[] args) {
		SpringApplication.run(EcommerceAppApplication.class, args);
	}

}
