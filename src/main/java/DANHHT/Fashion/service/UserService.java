package DANHHT.Fashion.service;

import DANHHT.Fashion.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    User getUser();
    List<User> getAllUsers();
    void saveInfo(User user);
}
