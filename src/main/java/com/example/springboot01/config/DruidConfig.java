package com.example.springboot01.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.catalina.manager.StatusManagerServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {
    //绑定配置yml中属性
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource getDruidDataSource(){
        return new DruidDataSource();
    }

    //后台监控功能
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //当访问这个页面的时候，就会进入监控
        ServletRegistrationBean<StatViewServlet>bean =new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        //后台需要有人登录，配置账号密码
        Map<String,String> initParameters = new HashMap<String,String>();
        //增加配置
        initParameters.put("loginUsername","admin");//登录是固定的key loginUsername loginPassword
        initParameters.put("loginPassword","123456");
        //允许谁可以访问
        initParameters.put("allow",""); //所有人都可以访问

        //禁止谁访问
        initParameters.put("gongchengjian","192.168.21.110");//禁止这个ip访问


        bean.setInitParameters(initParameters); //设置初始化参数
        return bean;
    }
    //过滤器 filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean= new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        //可以过滤哪些请求
        Map<String,String> initParameters = new HashMap<String,String>();

        //这些东西不进行统计
        initParameters.put("exclusions","*.js,*.css");

        bean.setInitParameters(initParameters);
        return bean;
    }


}
