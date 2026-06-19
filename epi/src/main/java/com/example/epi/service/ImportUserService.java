package com.example.epi.service;

import com.example.epi.Mapper.RemoteUserMapper;
import com.example.epi.entity.User;
import com.example.epi.Mapper.UserMapper;
import com.example.epi.enums.Role;
import com.example.epi.repository.UserRepository;
import com.example.epi.dto.RemotePayloadDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class ImportUserService {

    private final RemoteApiService remoteApiService;
    private final RemoteUserMapper remoteUserMapper;
    private final UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(ImportUserService.class);
    public void importUsers() {

        log.info("START importUsers()");

        List<RemotePayloadDTO> data = remoteApiService.fetchData();

        log.info("Fetched {} users from remote API", data.size());

        List<User> users = new ArrayList<>();

        for (RemotePayloadDTO p : data) {

            try {
                User user = remoteUserMapper.toEntity(p.getEmploye());
                String csp = p.getCSP().getCsp();

                log.debug("Processing CSP={}", csp);

                if (csp != null && csp.contains("DIRECTEUR")) {
                    user.setRole(Role.DIRECTEUR);
                } else {
                    user.setRole(Role.COLLABORATEUR);
                }

                users.add(user);

            } catch (Exception e) {
                log.error("Error mapping user: {}", p, e);
            }
        }

        userRepository.saveAll(users);

        log.info("Saved {} users successfully", users.size());
        log.info("END importUsers()");
    }
}

