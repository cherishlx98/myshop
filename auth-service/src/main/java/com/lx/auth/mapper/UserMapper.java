package com.lx.auth.mapper;

import com.lx.auth.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM `user` WHERE username = #{username}")
    @ResultMap(value = "userMap")
    User getUserByUsername(@Param("username") String username);

    @Insert("INSERT INTO `user`(open_id,username,password) VALUES(#{openId},#{username},#{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(User user);

    @Select("SELECT * FROM `user` WHERE id = #{id}")
    @Results(id = "userMap", value = {
            @Result(property = "openId", column = "open_id")
    })
    User getUserById(Long id);

    //动态SQL，属性不为空更新，为空不更新
    @Update("<script>Update `user`" +
            "<set>" +
            "   <if test=\"openId != null\">open_id=#{openId},</if>" +
            "   <if test=\"username != null\">username=#{username},</if>" +
            "   <if test=\"password != null\">password=#{password}</if>" +
            "</set>" +
            "WHERE id=#{id}</script>")
    int update(User user);

    @Select("SELECT * FROM `user` WHERE open_id = #{openId}")
    User getUserByOpenId(@Param("openId") String openId);

}
