package com.travellite2.travellite2.register.converters

import com.travellite2.travellite2.register.entity.User
import com.travellite2.travellite2.register.model.UserJson
import spock.lang.Specification

class UserToUserEntityConverterTestSpec extends Specification {

    def userToUserEntityConverter = new UserToUserEntityConverter()

    def "Convert"() {
        given:
        def x = 1

        when:
        x=x+1

        then:
        x == 2
    }

    def "should convert UserJson to User entity"() {
        given:
        UserJson userJson = new UserJson(
                firstName: 'John',
                lastName: 'Doe',
                userName: 'johndoe',
                password: 'password123',
                email: 'john.doe@example.com'
        )

        when:
        User userEntity = userToUserEntityConverter.convert(userJson)

        then:
        userEntity.firstName == userJson.firstName
        userEntity.lastName == userJson.lastName
        userEntity.userName == userJson.userName
        userEntity.password == userJson.password
        userEntity.email == userJson.email
    }
}
