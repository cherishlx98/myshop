package com.lx.good.impl;

import com.lx.good.api.GoodService;
import com.lx.good.entity.Good;
import com.lx.good.mapper.GoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodMapper goodMapper;

    public Good getById(Long id) {
        return goodMapper.getById(id);
    }

    public ArrayList<Good> getAll(int offset, int size) {
        return goodMapper.getAll(offset,size);
    }
}
