package com.zhixi.controller;

import com.zhixi.pojo.User;
import com.zhixi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhangzhixi
 * @version 1.0
 * @date 2021-12-14 10:17
 */
@Controller
public class UserController {

    @Autowired
    private IUserService userMapper;

    /**
     * @return 查询全部用户
     */
    @ResponseBody
    @RequestMapping("/queryUser")
    public List<User> queryUserAll() {
        List<User> list = userMapper.queryUserAll();
        for (User user : list) {
            System.out.println(user);
        }
        return list;
    }

    /**
     * @param id
     * @return 根据id查询用户
     */
    @ResponseBody
    @RequestMapping("/queryUserById/{id}")
    public User queryUserById(@PathVariable("id") int id) {
        User user = userMapper.queryUserById(id);
        System.out.println(user);
        return user;
    }

    /**
     * @param id
     * @return 根据id删除用户
     */
    @ResponseBody
    @RequestMapping("/delUser/{id}")
    public String delUser(@PathVariable("id") int id) {
        int i = userMapper.delUser(id);
        return i >= 1 ? "删除成功" : "删除失败";
    }

    /**
     * @return 根据id进行修改用户
     */
    @ResponseBody
    @RequestMapping("/updateUser/{id}/{userName}/{userPwd}")
    public String updateUser(@PathVariable("id") int id,
                             @PathVariable("userName") String userName,
                             @PathVariable("userPwd") String userPwd) {
        int i = userMapper.updateUser(new User(id, userName, userPwd));
        return i >= 1 ? "修改成功！" : "修改失败！";
    }

    /**
     * @return 添加用户
     */
    @ResponseBody
    @RequestMapping("/addUser/{id}/{userName}/{userPwd}")
    public String addUser(@PathVariable("id") int id,
                          @PathVariable("userName") String userName,
                          @PathVariable("userPwd") String userPwd) {
        int i = userMapper.addUser(new User(id, userName, userPwd));
        return i >= 1 ? "添加用户成功" : "添加用户失败";
    }
}
