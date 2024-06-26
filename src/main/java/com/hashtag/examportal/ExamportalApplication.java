package com.hashtag.examportal;

import com.hashtag.examportal.model.Role;
import com.hashtag.examportal.model.User;
import com.hashtag.examportal.model.UserRole;
import com.hashtag.examportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamportalApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ExamportalApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("Strating code....");

//		User usert =new User();
//
//		usert.setFirstName("admin");
//		usert.setLastName("admin");
//		usert.setEmail("admin@gmail.com");
//
//		Role role1 = new Role();
//		role1.setRoleId(44L);
//		role1.setRoleName("ADMIN");
//
//		Set<UserRole> userRoleSet=new HashSet<>();
//		UserRole userRole=new UserRole();
//
//		userRole.setRole(role1);
//		userRole.setUser(usert);
//
//		userRoleSet.add(userRole);
//
//		User user1=this.userService.createUser(usert,userRoleSet);
//		System.out.println(user1.getFirstName());
//
//		System.out.println(user1.getEmail());
	}

}
