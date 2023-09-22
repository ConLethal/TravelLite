package com.travellite2.travellite2.userOrder.repository;

import com.travellite2.travellite2.userOrder.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Integer> {

    UserOrder findByUserId(int userId);

    List<UserOrder> findBundleOrderIdByUserId(int userId);

  //  List<userOrder> getBundleOrderId(int bundleOrderId);

    List<UserOrder> findByBundleOrderId(int bundleOrderId);

    List<UserOrder> findByUserIdAndBundleOrderId(int userId, int bundleOrderId);

    @Query(
    value ="select coalesce(max(uo.bundleOrderId),0) from UserOrder uo where uo.userId = :userId")
    int getMaxBundleOrderId(int userId);



}
