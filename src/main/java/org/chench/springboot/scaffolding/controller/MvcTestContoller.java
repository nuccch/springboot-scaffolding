package org.chench.springboot.scaffolding.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于Controller单元测试
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.controller.MvcTestContoller
 * @date 2023.07.17
 */
@RestController
@RequestMapping("/mvctest")
public class MvcTestContoller {

    @GetMapping("/action")
    public Object action(HttpServletRequest req, HttpServletResponse resp) {
        return "Test is ok";
    }
}
