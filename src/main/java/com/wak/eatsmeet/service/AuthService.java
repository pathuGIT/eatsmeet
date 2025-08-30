package com.wak.eatsmeet.service;

import com.wak.eatsmeet.dto.LoginRequest;
import com.wak.eatsmeet.dto.TokenResponse;
import com.wak.eatsmeet.dto.UserRegisterResponse;
import com.wak.eatsmeet.model.user.Employees;
import com.wak.eatsmeet.model.user.Users;
import com.wak.eatsmeet.repository.user.EmployeeRepo;
import com.wak.eatsmeet.repository.user.UserRepo;
import jakarta.transaction.Transactional;
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
@Transactional
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
            if(role.equals("USER")) {
                System.out.println("2");
                Users users = userRepo.findByEmail(userDetails.getUsername());
                users.setRefresh_token(refreshToken);
                userRepo.save(users);
            }
//            if(role.equals("ADMIN")){
//                Employees emp = employeeRepo.findByEmail(userDetails.getUsername());
//                System.out.println("found: " + emp.getRefresh_token());
//                emp.setRefresh_token(null);
//                employeeRepo.updateRefreshTokenByEmail(userDetails.getUsername(), refreshToken);
//                System.out.println("found: " + emp.getRefresh_token());
//            }
             else if(role.equals("EMPLOYEE") || role.equals("ADMIN") || role.equals("SUB_ADMIN")){
                System.out.println("3");
                Employees emp = employeeRepo.findByEmail(userDetails.getUsername());
                System.out.println("4");
                emp.setRefresh_token(refreshToken);
                System.out.println("5");
                employeeRepo.save(emp);
                System.out.println("6");
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

    public Map<String, String> logout(String username, String role) {

        if(role.equals("USER")){
            Users user = userRepo.findByEmail(username);

            if (user != null) {
                user.setRefresh_token(null);
                userRepo.save(user);
                return Map.of("msg", "User "+ username + " successfully logout.");
            }
        }else{
            Employees emp = employeeRepo.findByEmail(username);

            if (emp != null) {
                emp.setRefresh_token(null);
                employeeRepo.save(emp);
                return Map.of("msg", "Emp "+ username + " successfully logout.");
            }
        }

        throw new RuntimeException("Error Logout.");
    }
}
