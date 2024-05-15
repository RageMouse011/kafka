package com.example.userservice.service;

import com.example.userservice.kafka.KafkaProducer;
import com.example.userservice.model.AppUser;
import com.example.userservice.repository.AppUserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final KafkaProducer kafkaProducer;

    @Transactional
    public AppUser addUser(AppUser appUser) {

        AppUser user;
        Optional<AppUser> userByUserNameOpt = appUserRepository.findAppUserByUsername(appUser.getUsername());

        if (userByUserNameOpt.isPresent()) {
            throw new EntityExistsException("This username is already in use");
        } else {
            user = appUserRepository.save(appUser);
            kafkaProducer.sendUserTopic(user.getUsername());
        }

        return user;
    }

    public AppUser getByUsername(String userName) {

        Optional<AppUser> userByUserNameOpt = appUserRepository.findAppUserByUsername(userName);

        if (userByUserNameOpt.isEmpty()) {
            throw new NullPointerException("This username is not found");
        }

        return userByUserNameOpt.get();
    }
}
