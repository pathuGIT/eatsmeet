package com.wak.eatsmeet.service;

import com.wak.eatsmeet.dto.LoginRequest;
import com.wak.eatsmeet.dto.TokenResponse;
import com.wak.eatsmeet.dto.UserRegisterResponse;
import com.wak.eatsmeet.model.user.Employees;
import com.wak.eatsmeet.model.user.Users;
import com.wak.eatsmeet.repository.user.EmployeeRepo;
import com.wak.eatsmeet.repository.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

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
        System.out.println("t1" + loginRequest.getLogin() + loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword())
        );
        System.out.println("t2");

        if(!authentication.isAuthenticated()){
            throw new RuntimeException("Invalid credentials");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String role = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");

        String activeToken = jwtService.generateActiveToken(userDetails.getUsername(), role);
        String refreshToken = jwtService.generateRefreshToken(userDetails.getUsername(), role);
        if(refreshToken != null){
            System.out.println("1");
            if(role.equals("USER")){
                System.out.println("2");
                Users users = userRepo.findById(Integer.valueOf(userDetails.getUsername())).orElseThrow(()-> new IllegalArgumentException("User not found with ID"));
                users.setRefresh_token(refreshToken);
                userRepo.save(users);
            } else if(role.equals("EMPLOYEE") || role.equals("ADMIN") || role.equals("SUB_ADMIN")){
                System.out.println("3");
                Employees emp = employeeRepo.findById(Integer.valueOf(userDetails.getUsername())).orElseThrow(()-> new IllegalArgumentException("Employee not found with ID"));
                emp.setRefresh_token(refreshToken);
                employeeRepo.save(emp);
            }
        }

        return new TokenResponse(activeToken, refreshToken);
    }

    public Map<String, String> getRefreshToken(String refreshToken) {
        try {
            String userLogin = jwtService.extractUserName(refreshToken);
            String role = jwtService.extractRole(refreshToken);

            System.out.println("t1"+ userLogin + role);
            UserDetails userDetails = User.withUsername(userLogin).password("").roles(role).build();
            if(jwtService.validateToken(refreshToken, userDetails)){
                String newActiveToken = jwtService.generateActiveToken(userLogin, role);
                return Map.of("activeToken", newActiveToken);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Error generate refresh token.");
    }
}
