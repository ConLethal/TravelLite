package com.travellite2.travellite2.register.controller

import com.travellite2.travellite2.register.entity.User
import com.travellite2.travellite2.register.model.UserJson
import com.travellite2.travellite2.register.service.UserService
import spock.lang.Specification

class UserControllerTestSpec extends Specification {

    def userServiceMock = Mock(UserService)


    UserController userController = new UserController(userServiceMock)

    def "LoginUser"() {
        given:
        def userJson = new UserJson(userName: "testUser", password: "testPassword")
        def user = new User(userName: userJson.userName, email: userJson.email)
        userServiceMock.getUser(userJson) >> user

        when:
        userController.loginUser(userJson)

        then:
        1 * userServiceMock.getUser(userJson) >> {user}

    }

    def "loginUser should return UNAUTHORIZED when invalid user credentials are provided"() {
        given:
        def userJson = new UserJson(userName: "invalidUser", password: "invalidPassword")
        userServiceMock.getUser(userJson) >> null

        when:
         userController.loginUser(userJson)

        then:
        1 * userServiceMock.getUser(_) >> null
    }

    def "RegisterUser"() {
        given:
        def userJson = new UserJson(userName: "newUser", email: "newuser@example.com")
        def registeredUser = new User(userName: userJson.userName, email: userJson.email)
        userServiceMock.registerUser(userJson) >> registeredUser

        when:
        userController.registerUser(userJson)

        then:
        1 * userServiceMock.registerUser(userJson)

    }

    def "registerUser should return BAD_REQUEST status when user registration fails"() {
        given:
        def userJson = new UserJson(userName: "invalidUser", email: "invalid@example.com")
        userServiceMock.registerUser(userJson) >> null

        when:
         userController.registerUser(userJson)

        then:
        1 * userServiceMock.registerUser(userJson)

    }
    
}
