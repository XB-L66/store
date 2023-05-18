package com.xb.store.mapper;

import com.xb.store.entity.Order;
import com.xb.store.entity.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface OrderMapper {
//    @Insert("""
//            insert into t_order(uid,aid,recv_name,recv_phone,recv_province,recv_city,recv_area,recv_address,total_price,status,order_time,pay_time,
//                                    created_user,created_time,modified_user,modified_time)
//                            values(#{uid}, #{aid}, #{recvName}, #{recvPhone}, #{recvProvince}, #{recvCity}, #{recvArea}, #{recvAddress},
//                                   #{totalPrice}, #{status}, #{orderTime}, #{payTime},
//                                      #{createdUser}, #{createdTime}, #{modifiedUser}, #{modifiedTime})
//
//            """)
//    @Options(useGeneratedKeys = true,keyProperty = "oid",keyColumn = "oid")
    Integer insertOrder(Order order);


    Integer insertOrderItem(OrderItem orderItem);
}
