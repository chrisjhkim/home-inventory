package com.chrisjhkim.sweethome.login.service;

import com.chrisjhkim.sweethome.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

	private final UserRepository userRepository;
}
