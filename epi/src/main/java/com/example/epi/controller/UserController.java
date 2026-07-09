package com.example.epi.controller;

import com.example.epi.Mapper.UserMapper;
import com.example.epi.dto.UserDTO;
import com.example.epi.entity.User;
import com.example.epi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.List;
import com.example.epi.Mapper.UserMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserDTO create(@RequestBody UserDTO Dto) {
        return userService.createUser(Dto);
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserDTO> getAll(){
        return userService.getAllUsers();
    }
    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Long id, @RequestBody UserDTO dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }


    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(@AuthenticationPrincipal Jwt jwt) {

        String email = jwt.getClaimAsString("email");

        User user = userService.findByEmail(email);

        return ResponseEntity.ok(userMapper.toDTO(user));
    }
}
