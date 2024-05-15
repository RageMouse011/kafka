package com.example.userservice.controller;

import com.example.userservice.model.AppUser;
import com.example.userservice.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping
    public AppUser addUser(@RequestBody AppUser appUser) {
        return appUserService.addUser(appUser);
    }

    @GetMapping
    public AppUser getByUsername(@RequestParam String userName) {
        return appUserService.getByUsername(userName);
    }
}
