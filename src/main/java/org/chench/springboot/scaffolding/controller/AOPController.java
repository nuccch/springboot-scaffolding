package org.chench.springboot.scaffolding.controller;

import org.chench.springboot.scaffolding.vo.JsonVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 用于演示AOP编程
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.controller.AOPController
 * @date 2023.07.17
 */
@RestController
@RequestMapping("/aop")
public class AOPController {
    @GetMapping("/m1")
    public Object m1() {
        System.out.println(String.format("执行目标方法m1:%s", new Date()));
        return JsonVO.build();
    }

    @GetMapping("/m2")
    public Object m2() throws IllegalArgumentException{
        System.out.println(String.format("执行目标方法m2:%s", new Date()));
        // 故意抛出一个异常，希望被异常通知拦截并处理
        if (1 == 1) {
            throw new IllegalArgumentException("非法参数");
        }
        return JsonVO.build();
    }
}
