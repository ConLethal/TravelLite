package com.travellite2.travellite2.userOrder.service;

import com.travellite2.travellite2.item.repository.ItemRepository;
import com.travellite2.travellite2.register.repository.UserRepository;
import com.travellite2.travellite2.userOrder.converter.UserOrderToItemRequestJsonConverter;
import com.travellite2.travellite2.userOrder.entity.UserOrder;
import com.travellite2.travellite2.userOrder.model.ItemRequest;
import com.travellite2.travellite2.userOrder.model.UserOrderRequest;
import com.travellite2.travellite2.userOrder.model.UserOrderSummary;
import com.travellite2.travellite2.userOrder.model.UserPreviousOrder;
import com.travellite2.travellite2.userOrder.repository.UserOrderRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserOrderService {

    private final UserOrderRepository userOrderRepository;
    private final UserRepository userRepository;

    private final UserOrderToItemRequestJsonConverter itemRequestConverter;

    private final ItemRepository itemRepository;

    public UserOrderService(UserOrderRepository userOrderRepository,
                            UserRepository userRepository,
                            UserOrderToItemRequestJsonConverter itemRequestConverter,
                            ItemRepository itemRepository) {
        this.userOrderRepository = userOrderRepository;
        this.userRepository = userRepository;
        this.itemRequestConverter = itemRequestConverter;
        this.itemRepository = itemRepository;
    }

    public void createBundle(UserOrderRequest userOrderRequest) {
        try {
            int userId = userRepository.findByUserName(userOrderRequest.getUserName()).getId();

            int bundleOrderId = 1;

            int maxBundleOrderIdByUserId = userOrderRepository.getMaxBundleOrderId(userId);

            if (maxBundleOrderIdByUserId != 0) {
                bundleOrderId = maxBundleOrderIdByUserId + 1;
            }

            for (ItemRequest itemRequest : userOrderRequest.getItemRequestList()) {

                UserOrder userOrder = itemRequestConverter.convert(itemRequest);

                userOrder.setBundleOrderId(bundleOrderId);
                userOrder.setUserId(userId);
                userOrderRepository.save(userOrder);
            }
        } catch (DataAccessException ex) {
            System.out.println("Exception occurred: " + ex.getMessage());
            throw ex;
        }
    }

    public List<UserOrderSummary> getOrderSummary(String userName, int bundleOrderId) {

        int userId = userRepository.findByUserName(userName).getId();

        List<UserOrder> userOrderList = userOrderRepository.findByUserIdAndBundleOrderId(userId, bundleOrderId);

        List<UserOrderSummary> userOrderSummaryList = new ArrayList<>();

        // Iterate through each userOrder and convert it to UserOrderSummary
        for (UserOrder userOrder : userOrderList) {
            UserOrderSummary userOrderSummary = new UserOrderSummary();

            // Populate UserOrderSummary with data from userOrder
            userOrderSummary.setItemName(itemRepository.findByItemId(userOrder.getItemId()).getItemName());
            userOrderSummary.setItemPrice(userOrder.getItemPrice());
            userOrderSummary.setItemQuantity(userOrder.getItemQuantity());
            userOrderSummary.setUserOrderId(userOrder.getUserOrderId());
            userOrderSummary.setBundleOrderId(userOrder.getBundleOrderId());

            // Add the UserOrderSummary to the result list
            userOrderSummaryList.add(userOrderSummary);
        }

        return userOrderSummaryList;


    }


    public int getBundleOrderIdByUserName(String username) {

        int userId = userRepository.findByUserName(username).getId();

       return userOrderRepository.getMaxBundleOrderId(userId);


    }

    public List<UserPreviousOrder> getAllUserOrdersByUserName(String userName) {
        //finds and converts username to userId
        int userId = userRepository.findByUserName(userName).getId();

        List<UserOrder> userOrderList =  userOrderRepository.findBundleOrderIdByUserId(userId);

        List<UserPreviousOrder> userOrdersList = new ArrayList<>();

        // Iterate through each userOrder and convert it to UserPreviousOrder
        for (UserOrder userOrder : userOrderList) {
            UserPreviousOrder userPreviousOrder = new UserPreviousOrder();

            // Populate UserPreviousOrder with data from userOrder
            userPreviousOrder.setItemName(itemRepository.findByItemId(userOrder.getItemId()).getItemName());
            userPreviousOrder.setItemPrice(userOrder.getItemPrice());
            userPreviousOrder.setItemQuantity(userOrder.getItemQuantity());
            userPreviousOrder.setUserOrderId(userOrder.getUserOrderId());
            userPreviousOrder.setBundleOrderId(userOrder.getBundleOrderId());

            // Add the UserPreviousOrder to the result list
            userOrdersList.add(userPreviousOrder);
        }

        return userOrdersList;

    }

}

