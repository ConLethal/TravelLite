package com.travellite2.travellite2.userOrder.controller;

import com.travellite2.travellite2.userOrder.model.UserOrderRequest;
import com.travellite2.travellite2.userOrder.model.UserOrderSummary;
import com.travellite2.travellite2.userOrder.model.UserPreviousOrder;
import com.travellite2.travellite2.userOrder.service.UserOrderService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order")
@CrossOrigin
public class UserOrderController {

    private final UserOrderService userOrderService;

    public UserOrderController(UserOrderService userOrderService) {
        this.userOrderService = userOrderService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBundle(@RequestBody UserOrderRequest request) {
        try {
            userOrderService.createBundle(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            // Handle exceptions appropriately, e.g., log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating bundle");
        }
    }

    @GetMapping("/ordersummary/{userName}/{bundleOrderId}")
    public ResponseEntity<List<UserOrderSummary>> getOrderSummary(@PathVariable String userName, @PathVariable int bundleOrderId) {

        List<UserOrderSummary> orderSummary = userOrderService.getOrderSummary(userName,bundleOrderId);

        return ResponseEntity.ok(orderSummary);

    }

    @GetMapping("/{username}")
    public int getBundleOrderId(@PathVariable String username) {

        return userOrderService.getBundleOrderIdByUserName(username);
    }

    @GetMapping("/previousorders/{userName}")
    public ResponseEntity<List<UserPreviousOrder>> getPreviousUserOrders(@PathVariable String userName) {

        List<UserPreviousOrder> orderSummary =  userOrderService.getAllUserOrdersByUserName(userName);

        return ResponseEntity.ok(orderSummary);
    }



}