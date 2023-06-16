package DANHHT.Fashion.service;

import DANHHT.Fashion.model.User;

public interface UserService {
    void save(User user);
    User getUser();

    void saveInfo(User user);
}
