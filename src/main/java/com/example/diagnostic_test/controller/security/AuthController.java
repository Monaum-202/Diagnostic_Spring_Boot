package com.example.diagnostic_test.controller.security;



import com.example.diagnostic_test.dto.payload.request.LoginRequest;
import com.example.diagnostic_test.dto.payload.request.SignupRequest;
import com.example.diagnostic_test.dto.payload.response.MessageResponse;
import com.example.diagnostic_test.dto.security.JwtResponse;
import com.example.diagnostic_test.entity.security.Role;
import com.example.diagnostic_test.entity.security.Users;
import com.example.diagnostic_test.enums.ERole;
import com.example.diagnostic_test.repository.security.RoleRepository;
import com.example.diagnostic_test.repository.security.UserRepository;

import com.example.diagnostic_test.security.jwt.JwtUtils;
import com.example.diagnostic_test.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

//@CrossOrigin(origins = "*", maxAge = 3600)
//for Angular Client (withCredentials)
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    Users users = new Users();


//    List<String> roles = userDetails.getAuthorities().stream()
//        .map(GrantedAuthority::getAuthority)
//        .toList();
    users.setUserName(userDetails.getUsername());
    users.setEmail(userDetails.getEmail());


    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);


    String token = jwtUtils.generateJwtToken(userDetails);


    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
            .body(new JwtResponse(users, token));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUserName(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    Users user = new Users(signUpRequest.getUsername(),
                         signUpRequest.getUserFirstName(),
                         signUpRequest.getUserLastName(),
                         signUpRequest.getEmail(),
                         encoder.encode(signUpRequest.getPassword())


            );

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findById(ERole.ROLE_USER.toString())
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findById(ERole.ROLE_ADMIN.toString())
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findById(ERole.ROLE_MODERATOR.toString())
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findById(ERole.ROLE_USER.toString())
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
     userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new MessageResponse("You've been signed out!"));
  }
}
