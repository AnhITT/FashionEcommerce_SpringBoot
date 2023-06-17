package DANHHT.Fashion.service.impl;

import DANHHT.Fashion.model.User;
import DANHHT.Fashion.repository.IRoleRepository;
import DANHHT.Fashion.repository.IUserRepository;
import DANHHT.Fashion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    public void save(User user){
        userRepository.save(user);
        Long userId = userRepository.getUserIdByUsername(user.getUsername());
        Long roleId = roleRepository.getRoleIdByName("User");
        if(roleId != 0 && userId != 0){
            userRepository.addRoleToUser(userId, roleId);
        }
    }

    @Override
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            String username = authentication.getName();
            var id = userRepository.getUserIdByUsername(username);
            var user = userRepository.getById(id);
            return user;
        }
        else
        {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public User getUserById(long id){
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent())
        {
            return optional.get();
        } else {
            throw new RuntimeException("User not found for id " + id);
        }
    }

    @Override
    public void deleteUserById(long id){
        this.userRepository.deleteById(id);
    }
    @Override
    public void saveInfo(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            String username = authentication.getName();
            var id = userRepository.getUserIdByUsername(username);
            User user1 = userRepository.getUsertById(id);
            user1.setFullName(user.getFullName());
            user1.setEmail(user.getEmail());
            user1.setAddress(user.getAddress());
            user1.setPhoneNumber(user.getPhoneNumber());
            userRepository.save(user1);
        }
    }
}
