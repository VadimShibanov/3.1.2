package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    @Override
    public void add(User user) {
        usersRepository.save(user);
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public User findOne(Integer id) {
        Optional<User> foundUser = usersRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Transactional
    public void update(Integer id, User updatedUser) {
        updatedUser.setId(id);
        usersRepository.save(updatedUser);

    }

    @Transactional
    public void delete(Integer id) {
        usersRepository.deleteById(id);
    }
}
