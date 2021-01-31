package com.lx.good.api;

import com.lx.good.entity.Good;

import java.util.ArrayList;

public interface GoodService {

    Good getById(Long id);

    ArrayList<Good> getAll(int offset, int size);
}
