package com.damon.security.springboot.dao;

import com.damon.security.springboot.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 **/
@Repository
public class UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;//JDBC提供的简单数据查询工具。它等同于使用MyBaits;

    //根据账号查询用户信息
    public UserDto getUserByUsername(String username){
        String sql = "select id,username,password,fullname,mobile from t_user where username = ?";
        /** 连接数据库查询用户,注意第二个参数需是一个数组；
         * UserDto.class指定了返回List的类型为UserDto,并且，这个类型UserDto必须和SQL语句select后检索
         * 出来的字段属性相同，也就是必须有对应的属性与之对应；否则出错；
         */
        List<UserDto> list = jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(UserDto.class));
        if(list !=null && list.size()==1){
            return list.get(0);
        }
        return null;
    }
}
