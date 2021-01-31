package com.lx.gateway.controller;

import com.lx.auth.api.AuthService;
import com.lx.auth.entity.User;
import com.lx.gateway.ResponseResult;
import com.lx.gateway.entity.OrderCreateJson;
import com.lx.order.api.OrderService;
import com.lx.order.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthService authService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseResult<Order> orderView(@PathVariable("id") Long id,
                                           @RequestParam(value = "token", defaultValue = "") String token){
        ResponseResult<Order> result = new ResponseResult<Order>();
        //1.登陆鉴权
        User user = authService.getUserByToken(token);
        if (user == null){
            result.setSuccess(false);
            result.setErrorCode(HttpStatus.UNAUTHORIZED.value());
            return result;
        }
        //2.查询订单，检验订单的userId和token的userId是否相等
        Order order = orderService.getById(id);
        if (order != null && order.getUserId().equals(user.getId())){
            result.setSuccess(true);
            result.setData(order);
        } else {
            result.setSuccess(false);
            result.setErrorCode(HttpStatus.UNAUTHORIZED.value());
        }
        return result;
    }

    /**
     * 分页查询从0页开始
     * @param page
     * @param pageSize
     * @param token
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/page/{page}/size/{size}")
    public ResponseResult<ArrayList<Order>> orderList(@PathVariable("page") int page,
                                                      @PathVariable("size") int pageSize,
                                                      @RequestParam("token") String token){
        ResponseResult<ArrayList<Order>> result = new ResponseResult<ArrayList<Order>>();
        //1.登陆鉴权
        User user = authService.getUserByToken(token);
        if(user == null){
            result.setSuccess(false);
            result.setErrorCode(HttpStatus.UNAUTHORIZED.value());
            return result;
        }
        //2.根据userId进行分页查询
        result.setSuccess(true);
        result.setData(orderService.getByUserId(user.getId(),page * pageSize, pageSize));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseResult<Order> orderCreate(@RequestBody OrderCreateJson json,
                                              @RequestParam(value = "token", defaultValue = "") String token){
        ResponseResult<Order> result = new ResponseResult<Order>();
        //1.登陆鉴权
        User user = authService.getUserByToken(token);
        if(user == null){
            result.setSuccess(false);
            result.setErrorCode(HttpStatus.UNAUTHORIZED.value());
        } else {
            //2.创建订单
            Order order = orderService.create(json.getGoodId(), user.getId(), json.getContact(), json.getAddress(), json.getPhone());
            result.setSuccess(!(order == null));
            result.setData(order);
        }
        return result;
    }

    /**
     * 这个测试没问题
     * @param id
     * @param token
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{id}/pay")
    public ResponseResult<String> orderPay(@PathVariable("id") Long id,
                                           @RequestParam("token") String token){
        ResponseResult<String> result = new ResponseResult<String>();
        //1.登陆鉴权
        User user = authService.getUserByToken(token);
        if(user == null){
            result.setSuccess(false);
            result.setErrorCode(HttpStatus.UNAUTHORIZED.value());
            return result;
        }
        //2.id是订单的id，先查询订单，和token的userId验证，相同则支付
        Order order = orderService.getById(id);
        if(order == null){
            result.setSuccess(false);
        } else {
            if (!order.getUserId().equals(user.getId())){
                result.setSuccess(false);
                result.setErrorCode(HttpStatus.UNAUTHORIZED.value());
            } else {
                String payId = orderService.pay(id);
                result.setSuccess(!(payId == null));
                result.setData(payId);
            }
        }
        return result;
    }
}
