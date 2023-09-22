package com.travellite2.travellite2.register.converters;

import org.springframework.stereotype.Component;

import com.travellite2.travellite2.register.entity.User;
import com.travellite2.travellite2.register.model.UserJson;


@Component
public class UserToUserEntityConverter {

    public User convert(UserJson user) {

        User userEntity = new User();
        //converts entity to Json
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setUserName(user.getUserName());
        userEntity.setPassWord(user.getPassword());
        userEntity.setEmail(user.getEmail());

        return userEntity;
    }

}
