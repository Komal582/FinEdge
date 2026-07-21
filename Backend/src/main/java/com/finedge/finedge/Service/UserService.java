package com.finedge.finedge.Service;

import com.finedge.finedge.Model.User;


public interface UserService {

    User saveUser(User user);

    boolean updateUser(User user);


}
