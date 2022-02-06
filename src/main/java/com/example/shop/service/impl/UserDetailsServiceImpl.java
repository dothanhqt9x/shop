package com.example.shop.service.impl;

import com.example.shop.model.entity.UserEntity;
import com.example.shop.repository.RoleRepository;
import com.example.shop.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserEntityByEmail(username);

        if (userEntity == null){
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User"+ username + " was not found in the database");
        }

        System.out.println("Found User: " + userEntity);

        // [ROLE_USER, ROLE_ADMIN,..]
        String roleNames = roleRepository.getRoleNameById(userEntity.getRoleId());

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNames != null){
            GrantedAuthority authority = new SimpleGrantedAuthority(roleNames);
            grantList.add(authority);
        }

        UserDetails userDetails = (UserDetails) new User(userEntity.getEmail(),
                userEntity.getPassword(), grantList);

        return userDetails;
    }
}
