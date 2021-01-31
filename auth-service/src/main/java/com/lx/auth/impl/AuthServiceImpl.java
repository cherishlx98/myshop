package com.lx.auth.impl;

import com.lx.auth.api.AuthService;
import com.lx.auth.entity.Token;
import com.lx.auth.entity.User;
import com.lx.auth.mapper.TokenMapper;
import com.lx.auth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @Component\@Autowired是spring-boot-starter项目的
 */
@Component
public class AuthServiceImpl implements AuthService {

    @Lazy @Autowired
    private TokenMapper tokenMapper;

    @Lazy @Autowired
    private UserMapper userMapper;


    public Token loginWithUsernameAndPassword(String username, String password) {
        User user = userMapper.getUserByUsername(username);
        if (user == null || !user.getPassword().equals(getSHA256Hashed(password))){
            return null;
        }
        Token token = new Token(generateToken(user), user.getId());
        //向token表中拆入一条记录
        tokenMapper.insert(token);
        return token;
    }

    public User getUserById(Long userId) {
        return userMapper.getUserById(userId);
    }

    //这里登出只是简单的从token表中删除一条记录
    public void logout(String token) {
        tokenMapper.delete(token);
    }

    public User getUserByToken(String token) {
        //1.根据String token 从token表中查出Token token
        Token tokenObject = tokenMapper.getByToken(token);
        if (tokenObject == null){
            return null;
        }
        //2.token.getUserId()获取userId 查询User
        return userMapper.getUserById(tokenObject.getUserId());
    }

    //插入到数据库中是密码加密后的密码
    public User createUser(String openId, String username, String password) {
        String hashPassword = getSHA256Hashed(password);
        if (hashPassword == null) {
            return null;
        }
        User user = new User(openId, username, hashPassword);
        userMapper.insert(user);
        return user;
    }

    //用户密码加密，这里是入口
    public String getSHA256Hashed(String source) {
        return getHashed(source, "SHA-256");
    }

//    private String getHashed(String source, String algorithm) {
//        String hash;
//        try {
//            //用SHA-256对source进行加密，得到byte[] digest
//            byte[] digest = MessageDigest.getInstance(algorithm).digest(source.getBytes("UTF-8"));
//            //把byte[] digest转换成十六进制的数的字符串
//            hash = DatatypeConverter.printHexBinary(digest);
//        } catch (NoSuchAlgorithmException | UnsupportedEncodingException |NullPointerException e) {
//            //catch ..|.. 是一种语言新特性
//            return null;
//        }
//        return hash;
//    }

    private String getHashed(String str, String type){
        /*
         * MD5、SHA1、SHA-256、SHA-512
         * */
        try {
            MessageDigest md = MessageDigest.getInstance(type);
            /*
             * update方法负责加密
             * 字符串转字节数组：str.getBytes("编码格式")
             * */
            md.update(str.getBytes());
            /*
             *获取摘要结果，加密后的数组
             * */
            byte[] bs = md.digest();

            /*
             * 变为16进制，使用字符串进行拼接
             * */
            StringBuilder res = new StringBuilder();
            for (byte b : bs) {
                //以16进制的格式输出整数类型的数值，输出域宽为2，右对齐，不足的用字符0替代
                res.append(String.format("%02X", b));
            }
            return res.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Token loginWithOpenId(String openId) {
        //1.根据openId查询User
        User user = userMapper.getUserByOpenId(openId);
        //2.生成token
        Token token = new Token(generateToken(user),user.getId());
        //3.向token表插入一条记录
        tokenMapper.insert(token);
        return token;
    }

    @Override
    public Boolean checkUserPassword(Long id, String password) {
        //1.根据id去查询用户
        User user = userMapper.getUserById(id);
        //2.判断用户的密码和加密后的password是否相等
        String userPassword = user.getPassword();
        if(userPassword != null && !userPassword.equals(getSHA256Hashed(password))){
            return false;
        }
        return true;
    }

    /**
     * spring事务处理，事务传播
     * @param id
     * @param password
     */
    @Override
    @Transactional
    public void setUserPassword(Long id, String password) {
        setUserHashedPassword(userMapper.getUserById(id),getSHA256Hashed(password));
    }

    @Transactional
    public void setUserHashedPassword(User user, String hashedPassword){
        if(user == null || hashedPassword == null){
             return;
        }
        //1.更新user的密码
        user.setPassword(hashedPassword);
        userMapper.update(user);
        //2.删除之前的token，之前的登陆信息全部失效
        for (Token token : tokenMapper.getByUserId(user.getId())){
            tokenMapper.delete(token.getToken());
        }
    }

    //生成令牌token，用到了随机数，保证每个user不同
    public String generateToken(User user){
        if(user == null){
            return null;
        }
        return getSHA256Hashed(user.toString() + new Random().nextInt());
    }
}
