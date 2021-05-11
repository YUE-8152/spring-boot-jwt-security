package com.yue.security.controller.user;

import com.yue.security.common.core.Result;
import com.yue.security.service.SysUserService;
import org.apache.commons.collections4.MapUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ProjectName: spring-boot-jwt-security
 * @Package: com.yue.security.controller.user
 * @ClassName: UserController
 * @Author: YUE
 * @Description:
 * @Date: 2021/5/11 9:24
 * @Version: 1.0
 */
@RestController
public class UserController {
    @DubboReference
    private SysUserService sysUserService;

    @PostMapping("/login")
    public Result selectList(@RequestBody Map map){
        return Result.success(sysUserService.loadUserByUsername(MapUtils.getString(map,"userName")));
    }
}
