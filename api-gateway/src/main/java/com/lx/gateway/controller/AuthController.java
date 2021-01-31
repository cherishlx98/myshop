package com.lx.gateway.controller;

import com.lx.auth.api.AuthService;
import com.lx.auth.entity.Token;
import com.lx.auth.entity.User;
import com.lx.gateway.ResponseResult;
import com.lx.gateway.entity.ChangePasswordJson;
import com.lx.gateway.entity.LoginJson;
import com.lx.gateway.entity.RegisterJson;
import com.lx.gateway.entity.UserLoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 用户名，密码登陆
     *
     * @param json
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseResult<UserLoginInfo> login(@RequestBody LoginJson json) {
        ResponseResult<UserLoginInfo> result = new ResponseResult<UserLoginInfo>();
        //根据json中的username,password登陆，返回Token(username,userId)
        //设置返回值result.setData(new UserLoginInfo(token.getToken(), userId, user.getUsername(), user.getOpenId()));
        String username = json.getUsername(), password = json.getPassword();
        Token token = authService.loginWithUsernameAndPassword(username, password);
        //用户名密码不匹配，token为null
        if (token == null) {
            result.setSuccess(false);
            return result;
        }
        Long userId = token.getUserId();
        //查询User，设置响应值
        User user = authService.getUserById(userId);

        result.setSuccess(true);
        result.setData(new UserLoginInfo(token.getToken(), userId, user.getUsername(), user.getOpenId()));
        return result;
    }

    /**
     * 登出注销
     * @param token
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/logout")
    public ResponseResult<Object> logout(@RequestParam(value = "token", defaultValue = "") String token) {
        ResponseResult<Object> result = new ResponseResult<Object>();
        authService.logout(token);
        result.setSuccess(true);
        return result;
    }

    /**
     * 鉴权，获取登陆的状态
     * @param token
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/status")
    public ResponseResult<UserLoginInfo> status(@RequestParam(value = "token", defaultValue = "") String token) {
        ResponseResult<UserLoginInfo> result = new ResponseResult<UserLoginInfo>();
        User user = authService.getUserByToken(token);
        if (user == null) {
            result.setSuccess(false);
            return result;
        }
        result.setSuccess(true);
        result.setData(new UserLoginInfo(token, user.getId(), user.getUsername(), user.getOpenId()));
        return result;
    }

    /**
     * 用户注册\登陆
     * @param json
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseResult<UserLoginInfo> register(@RequestBody RegisterJson json) {
        ResponseResult<UserLoginInfo> result = new ResponseResult<UserLoginInfo>();
        //1.用户注册
        User user = authService.createUser(json.getOpenId(), json.getUsername(), json.getPassword());
        //2.用户登陆，等一下，这里有问题，是用openId登陆，现在还没有测试
        Token token = authService.loginWithOpenId(json.getOpenId());
        result.setSuccess(true);
        result.setData(new UserLoginInfo(token.getToken(),token.getUserId(),user.getUsername(),user.getOpenId()));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/newpassword")
    public ResponseResult<Object> newPassword(@RequestBody ChangePasswordJson json){
        ResponseResult<Object> result = new ResponseResult<Object>();
        Long id = json.getId();
        String oldPassword = json.getOldPassword(),newPassword = json.getNewPassword();
        if(oldPassword == null || newPassword == null || oldPassword.equals(newPassword)){
            //使用日志的方式提示错误信息更好
            result.setSuccess(false);
            return result;
        }
        //1.先去判断用户的密码是否正确
        if(!authService.checkUserPassword(id,oldPassword)){
            result.setSuccess(false);
            return result;
        }
        //2.再去修改用户的密码
        authService.setUserPassword(id,newPassword);
        result.setSuccess(true);
        return result;
    }

    //下面就是微信授权登陆
}
