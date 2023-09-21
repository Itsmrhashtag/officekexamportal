package com.hashtag.examportal.controller;

import com.hashtag.examportal.model.Role;
import com.hashtag.examportal.model.User;
import com.hashtag.examportal.model.UserRole;
import com.hashtag.examportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('NORMAL')")
    public String dashboard(){
        return "USER DASHBOARD";
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user) throws Exception {

        user.setProfile("default.png");

        Set<UserRole> roles= new HashSet<>();
        Role role =new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");

        UserRole userRole=new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);


        roles.add(userRole);

        return this.userService.createUser(user,roles);

    }

    @GetMapping("/{username}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'NORMAL')")
    public User getUser (@PathVariable("username") String username){
        return this.userService.getUser(username);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);
    }

//    @PostMapping("/register")
//    public User registerUser(@RequestBody User user) throws Exception {
//        return userService.cr(user);
//    }

//    @PostMapping("/login")
//    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest) throws Exception {
//        return authService.loginUserService(loginRequest);
//    }
}
