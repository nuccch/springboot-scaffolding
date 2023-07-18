package org.chench.springboot.scaffolding.advice;

import org.chench.springboot.scaffolding.vo.JsonVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 全局异常拦截器
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.advice.GlobalExceptionAdvice
 * @date 2023.07.17
 */
@ControllerAdvice
public class GlobalExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object handleException(HttpServletRequest req, HttpServletResponse resp, Exception ex) {
        System.out.println(String.format("这里是@ControllerAdvice:%s", new Date()));
        logger.error("handle ex: {}", ex.getMessage(), ex);
        return JsonVO.build().setCode(-1).setMessage("error");
    }
}
