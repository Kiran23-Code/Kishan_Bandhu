package com.tce.kisanbandhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tce.kisanbandhu.model.GenericResponse;
import com.tce.kisanbandhu.model.Login;
import com.tce.kisanbandhu.service.LoginService;

@RestController
@RequestMapping("/authentication")
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value="/signup", produces = "application/json", method=RequestMethod.POST)
	public ResponseEntity<GenericResponse<Login>> createUser(@RequestBody Login login) {
		 GenericResponse<Login> obj = new GenericResponse<>();
			Login result = loginService.createUser(login);
			if(result == null) {
				obj.setCode(500);
				obj.setMessage("Something went wrong");
			} else {
				obj.setCode(200);
				obj.setMessage("success");
				obj.setData(result);
			}
			
			return ResponseEntity.ok(obj);
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value="/login",produces = "application/json", method=RequestMethod.GET)
	public ResponseEntity<GenericResponse<String>> login(@RequestHeader(value = "email") String email, @RequestHeader(value = "password")String password) {
		GenericResponse<String> obj = new GenericResponse<>();
		int status = loginService.login(email, password);
		obj.setCode(status);
		if(status == 401) {
			obj.setMessage("Invalid Credentails");
		} else if(status == 403) {
			obj.setMessage("User Not Found");
		} else {
			obj.setMessage("success");
		}
		
		
		return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(value="/test", method= RequestMethod.GET)
	public String test() {
		return "kisan-bandhu";
	}
}
