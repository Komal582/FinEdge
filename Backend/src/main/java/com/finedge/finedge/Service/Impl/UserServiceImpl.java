package com.finedge.finedge.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finedge.finedge.Model.User;
import com.finedge.finedge.Repository.UserRepository;
import com.finedge.finedge.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

     @Autowired
     private UserRepository userRepository;



    @Override
    public User saveUser(User user) {

        return userRepository.save(user);

    }

    @Override
    public boolean updateUser(User user){
          
        userRepository.save(user);
        return true;
    }


}
