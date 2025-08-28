package com.wak.eatsmeet.service;

import com.wak.eatsmeet.dto.LoginRequest;
import com.wak.eatsmeet.dto.TokenResponse;
import com.wak.eatsmeet.dto.UserRegisterResponse;
import com.wak.eatsmeet.model.user.Users;
import com.wak.eatsmeet.repository.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public UserRegisterResponse userRegister(Users users) {
        if(userRepo.existsByEmail(users.getEmail())){
            throw new IllegalArgumentException("This Email already exists.");
        }
        if(userRepo.existsByContact(users.getContact())){
            throw new IllegalArgumentException("This Email already exists.");
        }
        if(userRepo.existsByNic(users.getNic())){
            throw new IllegalArgumentException("This NIC number already exists.");
        }
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));

        Users res = userRepo.save(users);
        UserRegisterResponse ur = new UserRegisterResponse(res.getId(), res.getName(), res.getEmail(), res.getContact());
        return ur;
    }

    public TokenResponse verifyUser(LoginRequest loginRequest) {
        System.out.println("test1");
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword())
        );

        System.out.println("test2");
        if(!authentication.isAuthenticated()){
            throw new RuntimeException("Invalid credentials");
        }
        System.out.println("test3");

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String role = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");

        System.out.println("test4"+ userDetails.getUsername());
        String activeToken = jwtService.generateActiveToken(userDetails.getUsername(), role);

        System.out.println(activeToken);
        String refreshToken = jwtService.generateRefreshToken(userDetails.getUsername(), role);

        System.out.println("test5");
        return new TokenResponse(activeToken, refreshToken);
    }
}
