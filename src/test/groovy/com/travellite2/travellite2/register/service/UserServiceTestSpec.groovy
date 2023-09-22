package com.travellite2.travellite2.register.service

import com.travellite2.travellite2.register.converters.UserToUserEntityConverter
import com.travellite2.travellite2.register.entity.User
import com.travellite2.travellite2.register.model.UserJson
import com.travellite2.travellite2.register.repository.UserRepository
import spock.lang.Specification

class UserServiceTestSpec extends Specification {

    def userRepositoryMock = Mock(UserRepository)
    def userToUserEntityConverterMock = Mock(UserToUserEntityConverter)

    def userService = new UserService(userRepositoryMock,userToUserEntityConverterMock)

    def "GetUser"() {
        given:
        def userJson = new UserJson()
        userJson.userName = "username"
        userJson.password = "password"

        when:
        userService.getUser(userJson)

        then:
        1 * userRepositoryMock.getByUserNameAndPassword("username","password")

    }

    def "RegisterUser"() {
            given:
            def userJson = new UserJson(userName: "newUser", email: "new@example.com")
            def user = new User(userName: userJson.userName, email: userJson.email)

            userRepositoryMock.findByUserName(userJson.userName)
            userRepositoryMock.findByEmail(userJson.email)
            userRepositoryMock.save(user)

            when:
            userService.registerUser(userJson)


            then:
            1 * userRepositoryMock.findByUserName(userJson.userName)
            1 * userRepositoryMock.findByEmail(userJson.email)
            0 * userRepositoryMock.save(user)


        }

        def "should throw DuplicateUserNameException when username is duplicate"() {
            given:
            def userJson = new UserJson(userName: "existingUser", email: "existing@example.com")
            def user = new User(userName: userJson.userName, email: userJson.email)

            userRepositoryMock.findByUserName(userJson.userName)

            when:
            userService.registerUser(userJson)

            then:
            1 * userRepositoryMock.findByUserName(userJson.userName) >> {user}
            0 * userRepositoryMock.findByEmail(null)
            0 * userRepositoryMock.save(_)

            thrown(DuplicateUserNameException)
        }

        def "should throw DuplicateEmailException when email is duplicate"() {
            given:
            def userJson = new UserJson(userName: "newUser", email: "existing@example.com")
            def user = new User(userName: userJson.userName, email: userJson.email)

            userRepositoryMock.findByUserName(userJson.userName)
            userRepositoryMock.findByEmail(userJson.email)

            when:
            userService.registerUser(userJson)

            then:
            1 * userRepositoryMock.findByUserName(userJson.userName) >> {null}
            1 * userRepositoryMock.findByEmail(userJson.email) >> {user}
            0 * userRepositoryMock.save(_)

            thrown(DuplicateEmailException)
        }
}
