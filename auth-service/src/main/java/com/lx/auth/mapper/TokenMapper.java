package com.lx.auth.mapper;

import com.lx.auth.entity.Token;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface TokenMapper {

    /**
     * 删除token表一条记录，返回影响的行数
     * @param token
     * @return
     */
    @Delete("DELETE FROM `token` WHERE token = #{token}")
    int delete(String token);

    @Select("SELECT * FROM `token` WHERE token = #{token}")
    @Results(id = "tokenMap",value = {
            @Result(property = "userId", column = "user_id")
    })
    Token getByToken(@Param("token") String token);

    @Select("SELECT * from `token` WHERE user_id = #{userId}")
    @ResultMap("tokenMap")
    ArrayList<Token> getByUserId(@Param("userId") Long userId);

    @Insert("INSERT INTO `token`(token,user_id) VALUES(#{token},#{userId})")
    int insert(Token token);
}
