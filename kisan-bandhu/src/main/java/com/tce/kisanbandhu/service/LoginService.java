package com.tce.kisanbandhu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tce.kisanbandhu.model.Login;
import com.tce.kisanbandhu.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	LoginRepository loginRepository;
	
	public Login createUser(Login login) {
		return loginRepository.save(login);
	}
	
	public int login(String email, String password) {
		Optional<Login> login = loginRepository.findById(email);
		System.out.println("login: "+login.toString());
		if(login.isEmpty()) return 403;
		if(login.get().getPassword().equals(password)) {
			return 200;
		} else {
			return 401 ;
		}
	}
}
