package activities.com.backend.activities.services;

import activities.com.backend.activities.models.User;
import activities.com.backend.activities.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        try {
            return this.userRepository.findAll();
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all users");
        }
    }

    public User getUserById(long id){
        try {
            return userRepository.findById(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting user with id : "+id);
        }
    }

    public User addUser(User user){
        try {
            return this.userRepository.save(user);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding user: "+ user);
        }
    }

    public void deleteUser(long id){
        try {
            this.userRepository.deleteById(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding user");
        }
    }
}
