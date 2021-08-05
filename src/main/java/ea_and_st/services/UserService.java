package ea_and_st.services;

import com.ea_and_st.entities.User;
import com.ea_and_st.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> getUserById(Long id) {
        return repository.findByIdAndIsDeleted(id, false);
    }

    public User save(User user) {
        Date newDate = new Date();
        if (repository.existsByIdAndIsDeleted(user.getId(), false)) {
            User oldUser = repository.findByIdAndIsDeleted(user.getId(), false).get();
            user.setRegDate(oldUser.getRegDate());
            oldUser.setIsDeleted(true);
        } else {
            user.setRegDate(newDate);
        }
        user.setIsDeleted(false);
        return repository.save(user);
    }

    public boolean userExist(Long id) {
        return repository.existsById(id);
    }

    public Optional<User> getUserByEmailAndIsDeleted(String email, boolean isDeleted) {
        return repository.getUserByEmailAndIsDeleted(email, isDeleted);
    }

    public boolean userExistByEmail(String email) {
        return repository.existsByEmailAndIsDeleted(email, false);
    }

    public void setIsDeletedForUser(Long userId) {
        repository.getOne(userId).setIsDeleted(true);
        repository.save(repository.getOne(userId));
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public void updateFavoritesVersionAndSaveUser(User user) {
        Long date = new Date().getTime();
        user.setVersionFavorites(date);
        repository.save(user);
    }

    public void updateListsVersionAndSaveUser(User user) {
        Long date = new Date().getTime();
        user.setVersionLists(date);
        repository.save(user);
    }

    public void deleteUserById(Long userId) {
        repository.deleteById(userId);
    }
}
