package com.travellite2.travellite2.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travellite2.travellite2.register.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getByUserNameAndPassword(String userName, String password);

    User findByUserName(String userName);

    User findByEmail(String email);

}
