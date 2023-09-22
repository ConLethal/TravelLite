package com.travellite2.travellite2.userOrder.converter;

import com.travellite2.travellite2.userOrder.entity.UserOrder;
import com.travellite2.travellite2.userOrder.model.ItemRequest;
import org.springframework.stereotype.Component;

@Component
public class UserOrderToItemRequestJsonConverter {

    public UserOrder convert(ItemRequest itemRequest) {
        UserOrder userOrderEntity = new UserOrder();
        //converts entity to Json
        userOrderEntity.setItemId(itemRequest.getItemId());
        userOrderEntity.setItemQuantity(itemRequest.getItemQuantity());
        userOrderEntity.setItemPrice(itemRequest.getItemPrice());


        return userOrderEntity;
    }
}
