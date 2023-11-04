package com.hashtag.examportal.service.impl;

import com.hashtag.examportal.model.User;
import com.hashtag.examportal.model.UserRole;
import com.hashtag.examportal.repo.RoleRepository;
import com.hashtag.examportal.repo.UserRepository;
import com.hashtag.examportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

//    creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = this.userRepository.findByUsername(user.getUsername());
        if(local !=null){
            System.out.println("User is already there !!");
            throw new Exception("User already present !!");
        }else{
            for(UserRole ur:userRoles){
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local =this.userRepository.save(user);
            System.out.println(local.getAuthorities());
        }

        return local;
    }

    @Override
    public User getUser(String username) {
        if (userRepository.findByUsername(username) == null) {
            System.out.println("No User......................................");
            return null;
        }else{
            return  this.userRepository.findByUsername(username);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
