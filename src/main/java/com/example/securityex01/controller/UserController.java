package com.example.securityex01.controller;

import com.example.securityex01.domain.User;
import com.example.securityex01.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
   private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("")
    public String index(){
        return "index";
    }

    @GetMapping("/join")
    public void join(){

    }
    @GetMapping("login")
    public void login(){

    }

    @PostMapping("register")
    public String register(User user) {
        System.out.println("회원가입 진행 : " + user);
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("USER");
        userRepository.save(user);
        return "redirect:/";
    }

}
