package com.ping.wu.mongodbtest;

import com.ping.wu.model.User;

/**
 * Created by ping.wu on 2018/4/27.
 */
public interface UserDao {
    void saveUser(User user);
    User findUserByUserName(String userName);
    void updateUser(User user);
    void deleteUserById(Long id);
}
