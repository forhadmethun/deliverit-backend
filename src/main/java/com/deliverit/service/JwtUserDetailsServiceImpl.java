package com.deliverit.service;

import java.util.ArrayList;

import com.deliverit.service.interfaces.DataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final DataService dataService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("JwtUserDetailsServiceImpl:: loadUserByUsername:: userName: " + userName);
        var adminDto = dataService.findByUserName(userName);
        if (adminDto == null || !adminDto.getIsValid()) {
            log.info("JwtUserDetailsServiceImpl:: loadUserByUsername:: admin is null");
            throw new UsernameNotFoundException("User not found with userName: " + userName);
        }
        log.info("JwtUserDetailsServiceImpl:: loadUserByUsername:: admin: " + adminDto.toString());
        return new User(adminDto.getEmail(), adminDto.getPassword(), new ArrayList<>());
    }
}
