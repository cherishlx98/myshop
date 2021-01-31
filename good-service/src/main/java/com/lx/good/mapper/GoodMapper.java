package com.lx.good.mapper;

import com.lx.good.entity.Good;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface GoodMapper {

    @Select("SELECT * FROM `good` WHERE id = #{id}")
    @Results(id = "goodMap", value = {
            @Result(property = "shipCost", column = "ship_cost")
    })
    Good getById(@Param("id") Long id);

    @Select("SELECT * FROM `good` ORDER BY id DESC LIMIT #{offset},#{limit}")
    @ResultMap("goodMap")
    ArrayList<Good> getAll(@Param("offset") int offset, @Param("limit") int limit);
}
