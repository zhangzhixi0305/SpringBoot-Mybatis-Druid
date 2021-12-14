package com.zhixi.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 后台监控功能
     * 因为SpringBoot内置了Servlet容器，所以没有Web.xml，替代方法：ServletRegistrationBean
     *
     * @return Druid的配置
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {

        ServletRegistrationBean bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        /*后台需要有人登陆，账号密码配置*/
        HashMap<String, String> map = new HashMap<>();
        /*增加配置，登陆的key，是固定的，不能自己定义成其他的*/
        map.put("loginUsername", "admin");
        map.put("loginPassword", "123456");

        /*允许谁可以访问(IP白名单)*/
        /*map.put("allow", "127.0.0.1");*/
        /*设置ip黑名单*/
        /*map.put("deny", "127.0.0.1");*/

        /*初始化参数*/
        bean.setInitParameters(map);
        return bean;
    }


    /**
     * 配置 Druid 监控 之  web 监控的 filter
     * WebStatFilter：用于配置Web和Druid数据源之间的管理关联监控统计
     * @return Druid的过滤器设置
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        //exclusions：设置哪些请求进行过滤排除掉，从而不进行统计
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);

        //"/*" 表示过滤所有请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}