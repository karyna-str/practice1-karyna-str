package ua.opnu.practice1_template.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.opnu.practice1_template.model.User;
import ua.opnu.practice1_template.repository.UserRepository;
import ua.opnu.practice1_template.request.AuthRequest;
import ua.opnu.practice1_template.security.JwtProvider;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    public ApiController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {

        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        String role = request.getRole();
        if(role == null || (!role.equalsIgnoreCase("user") && !role.equalsIgnoreCase("admin"))){
            role = "user";  // роль за замовчуванням
        }
        user.setRole("ROLE_" + role.toUpperCase());
        userRepository.save(user);


        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        String token = jwtProvider.generateToken(authentication);

        return ResponseEntity.ok(Map.of("token", token));
    }
}
