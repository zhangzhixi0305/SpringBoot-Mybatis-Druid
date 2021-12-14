package com.zhixi.service;

import com.zhixi.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangzhixi
 * @version 1.0
 * @date 2021-12-14 10:14
 */
@Component
@Mapper
public interface IUserService {
    /**
     * 添加用户
     */
    int addUser(User user);

    /**
     * 修改用户
     */
    int updateUser(User user);

    /**
     * 删除用户
     */
    int delUser(int id);

    /**
     * 根据id查询用户
     */
    User queryUserById(@Param("userId") int id);

    /**
     * 查询全部用户
     */
    List<User> queryUserAll();
}
