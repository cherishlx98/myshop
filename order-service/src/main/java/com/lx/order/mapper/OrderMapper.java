package com.lx.order.mapper;

import com.lx.order.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM `order` WHERE id = #{id}")
    @Results(id = "orderMap", value = {
            @Result(property = "goodId", column = "good_id"),
            @Result(property = "payId", column = "pay_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "createdTime", column = "created_time")
    })
    Order getById(@Param("id") Long id);

    @Select("SELECT * FROM `order` WHERE user_id = #{userId} ORDER BY id LIMIT #{offset},#{size}")
    @ResultMap("orderMap")
    ArrayList<Order> getByUserId(@Param("userId") Long userId, @Param("offset") int offset, @Param("size") int size);

    @Insert("INSERT INTO `order`(good_id, pay_id, user_id, created_time, contact, address, phone)" +
            "VALUES(#{goodId}, #{payId}, #{userId}, #{createdTime}, #{contact}, #{address}, #{phone})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Order order);

    @Update("<script>Update `order`" +
            "<set>" +
            "   <if test=\"goodId != null\">good_id=#{goodId},</if>" +
            "   <if test=\"payId != null\">pay_id=#{payId},</if>" +
            "   <if test=\"userId != null\">user_id=#{userId},</if>" +
            "   <if test=\"createdTime != null\">created_time=#{createdTime},</if>" +
            "   <if test=\"contact != null\">contact=#{contact},</if>" +
            "   <if test=\"address != null\">address=#{address},</if>" +
            "   <if test=\"phone != null\">phone=#{phone}</if>" +
            "</set>" +
            "WHERE id=#{id}</script>")
    int update(Order order);
}
