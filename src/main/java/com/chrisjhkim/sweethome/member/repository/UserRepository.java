package com.chrisjhkim.sweethome.member.repository;

import com.chrisjhkim.sweethome.member.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
}
