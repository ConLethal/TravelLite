package com.travellite2.travellite2.userOrder.service

import com.travellite2.travellite2.item.entity.Item
import com.travellite2.travellite2.item.repository.ItemRepository
import com.travellite2.travellite2.register.entity.User
import com.travellite2.travellite2.register.repository.UserRepository
import com.travellite2.travellite2.userOrder.converter.UserOrderToItemRequestJsonConverter
import com.travellite2.travellite2.userOrder.entity.UserOrder
import com.travellite2.travellite2.userOrder.model.ItemRequest
import com.travellite2.travellite2.userOrder.model.UserOrderRequest
import com.travellite2.travellite2.userOrder.repository.UserOrderRepository
import spock.lang.Specification

class UserOrderServiceTest extends Specification {

    def userRepositoryMock = Mock(UserRepository)
    def itemRepositoryMock = Mock(ItemRepository)
    def userOrderRepositoryMock = Mock(UserOrderRepository)
    def itemRequestConverterMock = Mock(UserOrderToItemRequestJsonConverter)

    def userOrderService = new UserOrderService(
            userOrderRepositoryMock,
            userRepositoryMock,
            itemRequestConverterMock,
            itemRepositoryMock)

    def "createBundle should create a bundle successfully"() {
        given:
        def userOrderRequest = new UserOrderRequest(userName: "testUser",
                itemRequestList: [new ItemRequest(itemId: 1, itemQuantity: 2, itemPrice: 10.0)])

        def user = new User()
        user.userName = "username"
        user.id = 1

        def userOrder = new UserOrder()
        userOrder.setItemId(1)
        userOrder.setItemQuantity(2)
        userOrder.setItemPrice(10.0)
        userOrder.setBundleOrderId(1)
        userOrder.setUserId(1)

        when:
        userOrderService.createBundle(userOrderRequest)

        then:
        1 * userRepositoryMock.findByUserName(userOrderRequest.getUserName()) >> {user}
        1 * userOrderRepositoryMock.getMaxBundleOrderId(1) >> 0
        1 * itemRequestConverterMock.convert(_) >> {userOrder}
        1 * userOrderRepositoryMock.save(userOrder)

    }

    def "getOrderSummary should return a list of UserOrderSummary"() {
        given:

        def userName = "testUser"
        def bundleOrderId = 1
        def user = new User()
        user.userName = "username"
        user.id = 1

        def userOrder = new UserOrder()
        userOrder.setItemId(1)
        userOrder.setItemQuantity(2)
        userOrder.setItemPrice(10.0)
        userOrder.setBundleOrderId(1)
        userOrder.setUserId(1)

        def userOrderList = [userOrder]

        def item  = new Item()
        item.itemId = 1
        item.itemName = "item name"

        when:
        def result = userOrderService.getOrderSummary(userName, bundleOrderId)

        then:
        1 * userRepositoryMock.findByUserName(userName) >> {user}
        1 * userOrderRepositoryMock.findByUserIdAndBundleOrderId(1, bundleOrderId) >> {userOrderList}
        1 * itemRepositoryMock.findByItemId(_) >> {item}

        result[0].itemName == "item name"
        result[0].bundleOrderId == 1
    }

    def "getBundleOrderIdByUserName should return the bundle order ID"() {
        given:
        def userName = "testUser"
        def userId = 1 // Replace with the actual user ID

        when:
        def result = userOrderService.getBundleOrderIdByUserName(userName)

        then:
        1 * userRepositoryMock.findByUserName(userName) >> {
            def user = new User()
            user.id = userId
            return user
        }
        1 * userOrderRepositoryMock.getMaxBundleOrderId(userId) >> 1 // Replace with the desired bundleOrderId
        result == 1 // Replace with the desired bundleOrderId
    }

    def "getAllUserOrdersByUserName should return a list of UserPreviousOrder"() {
        given:

        def userName = "testUser"
        def user = new User()
        user.userName = "username"
        user.id = 1

        def userOrder = new UserOrder()
        userOrder.setItemId(1)
        userOrder.setItemQuantity(2)
        userOrder.setItemPrice(10.0)
        userOrder.setBundleOrderId(1)
        userOrder.setUserId(1)

        def userOrderList = [userOrder]

        def item  = new Item()
        item.itemId = 1
        item.itemName = "item name"

        when:
        def result = userOrderService.getAllUserOrdersByUserName(userName)
        then:
        1 * userRepositoryMock.findByUserName(userName) >> {user}
        1 * userOrderRepositoryMock.findBundleOrderIdByUserId(1) >> {userOrderList}
        1 * itemRepositoryMock.findByItemId(_) >> {item}

        result[0].itemName == "item name"
        result[0].bundleOrderId == 1

    }
}
