package com.travellite2.travellite2.register.service;

import org.springframework.stereotype.Component;

import com.travellite2.travellite2.register.model.UserJson;

import com.travellite2.travellite2.register.converters.UserToUserEntityConverter;
import com.travellite2.travellite2.register.entity.User;
import com.travellite2.travellite2.register.repository.UserRepository;

@Component
public class UserService {

    private final UserRepository userRepository;

    private final UserToUserEntityConverter userToUserEntityConverter;

    public UserService(UserRepository userRepository, UserToUserEntityConverter userToUserEntityConverter) {
        this.userRepository = userRepository;
        this.userToUserEntityConverter = userToUserEntityConverter;
    }

    public User getUser(UserJson userJson) {
        //gets the username and password, checking that it exists
        return userRepository.getByUserNameAndPassword(userJson.getUserName(),
                userJson.getPassword());

    }

    public User registerUser(UserJson user) {

        User userEntity = userToUserEntityConverter.convert(user);
        //checks to see if the username is a duplicate
        if (isUserNameDuplicate(user.getUserName())) {
            throw new DuplicateUserNameException("Username already exists. Please choose a different username.");
            //checks to see if the email is a duplicate
        } else if (isEmailDuplicate(user.getEmail())) {
            throw new DuplicateEmailException("Email already used. Please use a different email");
        } else
            return userRepository.save(userEntity);

    }

    private boolean isUserNameDuplicate(String userName) {
        return userRepository.findByUserName(userName) != null;
    }

    private boolean isEmailDuplicate(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
