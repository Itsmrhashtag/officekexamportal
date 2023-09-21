package com.hashtag.examportal.service;

import com.hashtag.examportal.model.User;
import com.hashtag.examportal.model.UserRole;

import java.util.Set;

public interface UserService {

    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    public User getUser(String username);

    public  void deleteUser(Long userId);
}
