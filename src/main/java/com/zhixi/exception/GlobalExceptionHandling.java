package com.zhixi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author zhangzhixi
 * @version 1.0
 * @date 2021-12-22 18:02
 */
@Slf4j
@ControllerAdvice // 实际上是一个组件，被增强
public class GlobalExceptionHandling {
    /**
     * 在请求中有可能不止一个出现错误，我们写个方法用来处理错误
     * 处理数学错误
     */
    @ExceptionHandler({ArithmeticException.class, NullPointerException.class})
    public String handlerArithmeticException(Exception exc) {
        log.error("捕获到的异常是：", exc);
        // 视图地址(如果发生了数学运算异常，跟空指针异常，就会跳转到视图地址)
        return "upload";
    }
}
