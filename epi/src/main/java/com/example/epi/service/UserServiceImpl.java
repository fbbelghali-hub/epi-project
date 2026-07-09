package com.example.epi.service;

import com.example.epi.dto.UserDTO;
import com.example.epi.entity.User;
import com.example.epi.Mapper.UserMapper;
import com.example.epi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(UserDTO dto) {

        log.info("CREATE user {}", dto.getEmail());

        User user = userMapper.toEntity(dto);
        User saved = userRepository.save(user);

        log.info("User created with id={}", saved.getId());

        return userMapper.toDTO(saved);
    }

    //Rest template
    @Override
    public UserDTO getUserById(Long id) {

        log.info("GET user id={}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found id={}", id);
                    return new RuntimeException("User not found");
                });

        return userMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserDTO> usersDTO = new ArrayList<>();

        for (User user : users) {
            usersDTO.add(userMapper.toDTO(user));
        }

        return usersDTO;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setMatricule(dto.getMatricule());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setJobTitle(dto.getJobTitle());

        User updated = userRepository.save(user);
        return userMapper.toDTO(updated);
    }

    @Override
    public void deleteUser(Long id) {

        log.warn("DELETE user id={}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);

        log.info("User deleted id={}", id);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }
}