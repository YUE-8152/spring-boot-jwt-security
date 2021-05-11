package com.yue.security.common.core;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ProjectName: cln-school-app
 * @Package: com.cln.school.common.core
 * @ClassName: ExceptionAdvice
 * @Author: YUE
 * @Description:
 * @Date: 2020/8/15 13:41
 * @Version: 1.0
 */
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return Result.fail(ResultCode.PARAMETERS_ERROR.code(), objectError.getDefaultMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public Result ServiceExceptionHandler(ServiceException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }

}
