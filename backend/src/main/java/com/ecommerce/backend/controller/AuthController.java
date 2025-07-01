// // // // package com.ecommerce.backend.controller;

// // // // import com.ecommerce.backend.dto.AuthResponse;
// // // // import com.ecommerce.backend.dto.LoginRequest;
// // // // import com.ecommerce.backend.dto.RegisterRequest;
// // // // import com.ecommerce.backend.model.User;
// // // // import com.ecommerce.backend.repository.UserRepository;
// // // // import com.ecommerce.backend.service.JwtService;
// // // // import lombok.RequiredArgsConstructor;

// // // // import org.springframework.http.HttpStatus;
// // // // import org.springframework.http.ResponseEntity;
// // // // import org.springframework.security.authentication.AuthenticationManager;
// // // // import org.springframework.security.authentication.BadCredentialsException;
// // // // import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// // // // import org.springframework.security.core.userdetails.UsernameNotFoundException;
// // // // import org.springframework.security.crypto.password.PasswordEncoder;
// // // // import org.springframework.web.bind.annotation.GetMapping;
// // // // import org.springframework.web.bind.annotation.PostMapping;
// // // // import org.springframework.web.bind.annotation.RequestBody;
// // // // import org.springframework.web.bind.annotation.RequestMapping;
// // // // import org.springframework.web.bind.annotation.RestController;
// // // // import java.util.Map;

// // // // @RestController
// // // // @RequestMapping("/api/auth")
// // // // @RequiredArgsConstructor
// // // // public class AuthController {

// // // //     private final UserRepository userRepository;
// // // //     private final PasswordEncoder passwordEncoder;
// // // //     private final JwtService jwtService;
// // // //     private final AuthenticationManager authenticationManager;

// // // //     @PostMapping("/register")
// // // //     public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
// // // //         if (userRepository.findByEmail(request.getEmail()).isPresent()) {
// // // //             return ResponseEntity.badRequest().body("Error: Email is already in use!");
// // // //         }
// // // //         User user = User.builder()
// // // //                 .name(request.getName())
// // // //                 .email(request.getEmail())
// // // //                 .password(passwordEncoder.encode(request.getPassword()))
// // // //                 .role(User.Role.ROLE_USER)
// // // //                 .build();
// // // //         userRepository.save(user);
// // // //         return ResponseEntity.ok("User registered successfully!");
// // // //     }

// // // //   @PostMapping("/login")
// // // // public ResponseEntity<?> login(@RequestBody LoginRequest request) {
// // // //     // First, log what we received
// // // //     System.out.println("==== LOGIN ATTEMPT RECEIVED ====");
// // // //     System.out.println("Email: " + request.getEmail());
    
// // // //     try {
// // // //         // Skip actual authentication for now - just return a success response
// // // //         return ResponseEntity.ok(Map.of(
// // // //             "message", "Login successful for testing",
// // // //             "email", request.getEmail(),
// // // //             "token", "test-token-123"
// // // //         ));
// // // //     } catch (Exception e) {
// // // //         System.err.println("==== LOGIN ERROR ====");
// // // //         e.printStackTrace();
// // // //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
// // // //             .body(Map.of("error", e.getMessage()));
// // // //     }
// // // // }

// // // // // Add a simple test endpoint to check connectivity
// // // // @GetMapping("/test")
// // // // public String test() {
// // // //     return "Auth API is working!";
// // // // }
// // // // @GetMapping("/test")
// // // // public ResponseEntity<String> testAuth() {
// // // //     return ResponseEntity.ok("Auth endpoint is accessible!");
// // // // }
// // // // }

// // // package com.ecommerce.backend.controller;

// // // import com.ecommerce.backend.dto.AuthResponse;
// // // import com.ecommerce.backend.dto.LoginRequest;
// // // import com.ecommerce.backend.dto.RegisterRequest;
// // // import com.ecommerce.backend.model.User;
// // // import com.ecommerce.backend.repository.UserRepository;
// // // import com.ecommerce.backend.service.JwtService;
// // // import lombok.RequiredArgsConstructor;

// // // import org.springframework.http.HttpStatus;
// // // import org.springframework.http.ResponseEntity;
// // // import org.springframework.security.authentication.AuthenticationManager;
// // // import org.springframework.security.authentication.BadCredentialsException;
// // // import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// // // import org.springframework.security.core.userdetails.UsernameNotFoundException;
// // // import org.springframework.security.crypto.password.PasswordEncoder;
// // // import org.springframework.web.bind.annotation.GetMapping;
// // // import org.springframework.web.bind.annotation.PostMapping;
// // // import org.springframework.web.bind.annotation.RequestBody;
// // // import org.springframework.web.bind.annotation.RequestMapping;
// // // import org.springframework.web.bind.annotation.RestController;
// // // import java.util.Map;

// // // @RestController
// // // @RequestMapping("/api/auth")
// // // @RequiredArgsConstructor
// // // public class AuthController {

// // //     private final UserRepository userRepository;
// // //     private final PasswordEncoder passwordEncoder;
// // //     private final JwtService jwtService;
// // //     private final AuthenticationManager authenticationManager;

// // //     @PostMapping("/register")
// // //     public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
// // //         System.out.println("Register request received for: " + request.getEmail());
        
// // //         if (userRepository.findByEmail(request.getEmail()).isPresent()) {
// // //             return ResponseEntity.badRequest().body("Error: Email is already in use!");
// // //         }
// // //         User user = User.builder()
// // //                 .name(request.getName())
// // //                 .email(request.getEmail())
// // //                 .password(passwordEncoder.encode(request.getPassword()))
// // //                 .role(User.Role.ROLE_USER)
// // //                 .build();
// // //         userRepository.save(user);
// // //         return ResponseEntity.ok("User registered successfully!");
// // //     }

// // //     @PostMapping("/login")
// // //     public ResponseEntity<?> login(@RequestBody LoginRequest request) {
// // //         // First, log what we received
// // //         System.out.println("==== LOGIN ATTEMPT RECEIVED ====");
// // //         System.out.println("Email: " + request.getEmail());
        
// // //         try {
// // //             // Return a response with the exact format the frontend expects
// // //             return ResponseEntity.ok(Map.of(
// // //                 "token", "test-token-123",
// // //                 "email", request.getEmail(),
// // //                 "message", "Login successful for testing"
// // //             ));
// // //         } catch (Exception e) {
// // //             System.err.println("==== LOGIN ERROR ====");
// // //             e.printStackTrace();
// // //             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
// // //                 .body(Map.of("error", e.getMessage()));
// // //         }
// // //     }

// // //     // Remove the duplicate endpoint - keep only one test endpoint
// // //     @GetMapping("/test")
// // //     public ResponseEntity<String> testAuth() {
// // //         return ResponseEntity.ok("Auth endpoint is accessible!");
// // //     }
// // // }

// // package com.ecommerce.backend.controller;

// // import com.ecommerce.backend.dto.AuthResponse;
// // import com.ecommerce.backend.dto.LoginRequest;
// // import com.ecommerce.backend.dto.RegisterRequest;
// // import com.ecommerce.backend.model.User;
// // import com.ecommerce.backend.repository.UserRepository;
// // import com.ecommerce.backend.service.JwtService;
// // import lombok.RequiredArgsConstructor;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.security.authentication.AuthenticationManager;
// // import org.springframework.security.authentication.BadCredentialsException;
// // import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// // import org.springframework.security.core.userdetails.UserDetails;
// // import org.springframework.security.crypto.password.PasswordEncoder;
// // import org.springframework.web.bind.annotation.*;

// // @RestController
// // @RequestMapping("/api/auth")
// // @RequiredArgsConstructor
// // public class AuthController {

// //     private final UserRepository userRepository;
// //     private final PasswordEncoder passwordEncoder;
// //     private final JwtService jwtService;
// //     private final AuthenticationManager authenticationManager;

// //     @PostMapping("/register")
// //     public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
// //         if (userRepository.findByEmail(request.getEmail()).isPresent()) {
// //             return ResponseEntity.badRequest().body("Error: Email is already in use!");
// //         }
// //         User user = User.builder()
// //                 .name(request.getName())
// //                 .email(request.getEmail())
// //                 .password(passwordEncoder.encode(request.getPassword()))
// //                 .role(User.Role.ROLE_USER)
// //                 .build();
// //         userRepository.save(user);
// //         return ResponseEntity.ok("User registered successfully!");
// //     }

// //     @PostMapping("/login")
// //     public ResponseEntity<?> login(@RequestBody LoginRequest request) {
// //         try {
// //             // This is the core of Spring Security authentication
// //             authenticationManager.authenticate(
// //                 new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
// //             );

// //             // If authentication is successful, find the user and generate a token
// //             final UserDetails userDetails = userRepository.findByEmail(request.getEmail())
// //                     .orElseThrow(() -> new Exception("User not found"));
            
// //             final String jwt = jwtService.generateToken(userDetails);

// //             // Return the token in the response
// //             return ResponseEntity.ok(new AuthResponse(jwt));

// //         } catch (BadCredentialsException e) {
// //             // Handle incorrect password or username
// //             return ResponseEntity.status(401).body("Invalid credentials");
// //         } catch (Exception e) {
// //             return ResponseEntity.status(500).body("An error occurred during login");
// //         }
// //     }

// //     @GetMapping("/test")
// //     public ResponseEntity<String> testAuth() {
// //         return ResponseEntity.ok("Auth endpoint is accessible!");
// //     }
// // }
// package com.ecommerce.backend.controller;

// import com.ecommerce.backend.dto.AuthResponse;
// import com.ecommerce.backend.dto.LoginRequest;
// import com.ecommerce.backend.dto.RegisterRequest;
// import com.ecommerce.backend.model.User;
// import com.ecommerce.backend.repository.UserRepository;
// import com.ecommerce.backend.service.JwtService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/auth")
// @RequiredArgsConstructor
// public class AuthController {

//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;
//     private final JwtService jwtService;
//     private final AuthenticationManager authenticationManager;
//     private final UserDetailsService userDetailsService;

//     @PostMapping("/register")
//     public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
//         if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//             return ResponseEntity.badRequest().body("Error: Email is already in use!");
//         }
//         User user = User.builder()
//                 .name(request.getName())
//                 .email(request.getEmail())
//                 .passwordHash(passwordEncoder.encode(request.getPassword()))
//                 .role("ROLE_USER")
//                 .build();
//         userRepository.save(user);
//         return ResponseEntity.ok("User registered successfully!");
//     }

//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//         try {
//             authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//             );

//             // Use the UserDetailsService to load the user for JWT
//             final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
//             final String jwt = jwtService.generateToken(userDetails);

//             return ResponseEntity.ok(new AuthResponse(jwt));

//         } catch (BadCredentialsException e) {
//             return ResponseEntity.status(401).body("Invalid credentials");
//         } catch (Exception e) {
//             return ResponseEntity.status(500).body("An error occurred during login");
//         }
//     }

//     @GetMapping("/test")
//     public ResponseEntity<String> testAuth() {
//         return ResponseEntity.ok("Auth endpoint is accessible!");
//     }
// }
package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.AuthResponse;
import com.ecommerce.backend.dto.LoginRequest;
import com.ecommerce.backend.dto.RegisterRequest;
import com.ecommerce.backend.model.User;
import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final org.springframework.security.core.userdetails.UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_USER") // <--  ROLE_USER
                .build();

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}