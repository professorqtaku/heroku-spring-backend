package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class LoginController {
  
  @Autowired
  private UserService userService;
  
  @PostMapping("/login")
  public User login(@RequestBody User user, HttpServletRequest req) {
    return userService.login(user, req);
  }
  
  @PostMapping("/register")
  public User register(@RequestBody User user) {
    return userService.createUser(user);
  }
  
  @GetMapping("/whoami")
  public User whoAmI() {
    return userService.findCurrentUser();
  }
}
