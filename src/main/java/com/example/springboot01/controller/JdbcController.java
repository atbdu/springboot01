package com.example.springboot01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class JdbcController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/selectDevice")
    @ResponseBody
    //查询
    public String deviceList(){
        String sql = "select * from device";
        List<Map<String,Object>>mapList = jdbcTemplate.queryForList(sql);
        for (int i =0;i< mapList.size();i++) {
            System.out.println(mapList.get(i));
        }
        return "ok";
    }
    //删除
    @RequestMapping("/deleteDevice")
    @ResponseBody
    public String deleteDevcie(){
        String sql = "delete from device where device_no='88888888'";
        int line = jdbcTemplate.update(sql);
        System.out.println("==============>"+line);
        return "ok";
    }
    //修改
    @RequestMapping("/updateDevice")
    @ResponseBody
    public String updateDevcie(){
        String sql = "update device set device_no='777777777' where device_no='888888'";
        int line = jdbcTemplate.update(sql);
        System.out.println("==============>"+line);
        return "ok";
    }
}
