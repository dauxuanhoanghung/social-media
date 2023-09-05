/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.apis;

import com.social.jwt.JwtTokenProvider;
import com.social.dto.request.UserCredentialRequest;
import com.social.dto.response.JwtResponse;
import com.social.dto.response.ModelResponse;
import com.social.services.UserService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author DinhChuong
 */
@RestController
public class AuthenticateAPI {
    
    @Autowired  
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<ModelResponse> createAuthenticationToken(@RequestBody UserCredentialRequest authenticationRequest) throws Exception {
        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            final UserDetails userDetails = userService
                    .loadUserByUsername(authenticationRequest.getUsername());

            final String token = jwtTokenProvider.generateToken(userDetails);
            final Date expirationDate = this.jwtTokenProvider.getExpirationDateFromToken(token);
            String ex = expirationDate.toString();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ModelResponse("200","Get auth token successfully", new JwtResponse(token,ex))
            );
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ModelResponse("400","Get auth token failed", null)
            );
        }
    }
    
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
