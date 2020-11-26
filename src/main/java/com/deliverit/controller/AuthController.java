package com.deliverit.controller;

import com.deliverit.config.jwt.JwtTokenUtil;
import com.deliverit.service.JwtUserDetailsServiceImpl;
import com.deliverit.utility.exceptions.ServiceError;
import com.deliverit.utility.request.JwtRequest;
import com.deliverit.utility.response.JwtResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsServiceImpl userDetailsService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final var userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        log.info("JwtAuthenticationController:: userDetails: " + userDetails.toString());

        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String userName, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        } catch (DisabledException e) {
            throw new Exception(ServiceError.USER_DISABLED, e);
        } catch (BadCredentialsException e) {
            throw new Exception(ServiceError.INVALID_CREDENTIALS, e);
        }
    }
}
