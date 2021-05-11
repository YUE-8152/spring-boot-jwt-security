package com.yue.security.controller.test;

import com.yue.security.bean.user.LoginUserPo;
import com.yue.security.bean.user.UserContext;
import com.yue.security.common.core.Result;
import com.yue.security.service.SysUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: springboot-authority
 * @Package: com.yue.base.controller.test
 * @ClassName: TestController
 * @Author: YUE
 * @Description:
 * @Date: 2021/5/10 15:38
 * @Version: 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @DubboReference
    private SysUserService sysUserService;

    @GetMapping("/list")
    public Result selectList(){
        LoginUserPo user = UserContext.getUser();
        return Result.success(user);
    }
}
