package com.nile.apiservice.security.service;

import com.nile.apiservice.security.exception.exceptions.CUserNotFoundException;
import com.nile.apiservice.security.repository.UserJpaRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService { // ! Member 패키지의 것과 중복  implements UserDetailsService 코드 삭제

    private final UserJpaRepository userJpaRepository;

    public UserDetails loadUserByUsername(String userPk) {
        return userJpaRepository.findById(Long.valueOf(userPk)).orElseThrow(CUserNotFoundException::new);
    }

    public void signUpUser() {
        
    }
}