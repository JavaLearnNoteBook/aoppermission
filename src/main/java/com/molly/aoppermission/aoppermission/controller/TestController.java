package com.molly.aoppermission.aoppermission.controller;

import com.molly.aoppermission.aoppermission.annotation.RebateinPermission;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class TestController {
    /**
     * 配置权限注解 @VisitPermission("permission-test")
     * 只用拥有该权限的用户才能访问，否则提示非法操作
     */
    @RebateinPermission(permissionValue = "126")
    @GetMapping("/test")
    public String test() {
        System.out.println("================== step 3: doing ==================");
        return "success";
    }
}
