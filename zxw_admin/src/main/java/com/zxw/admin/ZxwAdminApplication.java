package com.zxw.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zxw.admin.**.**.mapper")
public class ZxwAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZxwAdminApplication.class, args);
        System.out.println("<~|~><~|~><~|~><~|~>启动成功<~|~><~|~><~|~><~|~>");
    }

}
