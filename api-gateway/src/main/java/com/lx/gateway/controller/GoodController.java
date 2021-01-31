package com.lx.gateway.controller;

import com.lx.gateway.ResponseResult;
import com.lx.good.api.GoodService;
import com.lx.good.entity.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/goods")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseResult<Good> goodView(@PathVariable("id") Long id){
        ResponseResult<Good> result = new ResponseResult<Good>();
        Good good = goodService.getById(id);
        if(good == null){
            result.setSuccess(false);
            return result;
        }
        result.setSuccess(true);
        result.setData(good);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/page/{page}/size/{size}")
    public ResponseResult<ArrayList<Good>> goodList(@PathVariable("page") int page,
                                                    @PathVariable("size") int pageSize){
        ResponseResult<ArrayList<Good>> result = new ResponseResult<ArrayList<Good>>();
        ArrayList<Good> list = goodService.getAll(page * pageSize, pageSize);
        if(list == null){
            result.setSuccess(false);
            return result;
        }
        result.setSuccess(true);
        result.setData(list);
        return result;
    }
}
