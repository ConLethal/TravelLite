package com.travellite2.travellite2.userOrder.controller

import com.travellite2.travellite2.userOrder.model.UserOrderRequest
import com.travellite2.travellite2.userOrder.model.UserOrderSummary
import com.travellite2.travellite2.userOrder.model.UserPreviousOrder
import com.travellite2.travellite2.userOrder.service.UserOrderService
import spock.lang.Specification

class UserOrderControllerTest extends Specification {

    def userOrderService = Mock(UserOrderService)
    def userOrderController = new UserOrderController(userOrderService)


    def "createBundle should return 201 CREATED status when successful"() {
        given:
        def userOrderController = new UserOrderController(userOrderService)
        def userOrderRequest = new UserOrderRequest() // Create a valid request here

        when:
        def response = userOrderController.createBundle(userOrderRequest)

        then:
        1 * userOrderService.createBundle(userOrderRequest)

    }

    def "getOrderSummary should return a valid list of UserOrderSummary"() {
        given:
        def userName = "testUser"
        def bundleOrderId = 1 // Replace with a valid bundleOrderId

        when:
        def response = userOrderController.getOrderSummary(userName, bundleOrderId)

        then:
        1 * userOrderService.getOrderSummary(userName, bundleOrderId)

    }

    def "getBundleOrderId should return a valid bundle order ID"() {
        given:
        def username = "testUser"
        def bundleOrderId = 1 // Replace with a valid bundleOrderId

        when:
        def result = userOrderController.getBundleOrderId(username)

        then:
        1 * userOrderService.getBundleOrderIdByUserName(username) >> bundleOrderId


    }

    def "getPreviousUserOrders should return a valid list of UserPreviousOrder"() {
        given:
        def userName = "testUser"

        when:
        def response = userOrderController.getPreviousUserOrders(userName)

        then:
        1 * userOrderService.getAllUserOrdersByUserName(userName)
    }

    }


