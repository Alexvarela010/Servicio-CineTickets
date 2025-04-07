package com.cinetickets.serviciocinetickets.service;


import com.cinetickets.serviciocinetickets.entities.UserInfo;
import com.cinetickets.serviciocinetickets.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository; // repositorio para acceder a la base de datos
    @Autowired
    private PasswordEncoder encoder; // password encoder
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = repository.findByEmail(email);
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added Successfully";
    }
}
