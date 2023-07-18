package org.chench.springboot.scaffolding.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * 使用Spring的STOMP实现WebSocket推送
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.controller.StompController
 * @date 2023.07.17
 */
@Controller
public class StompController {

    @MessageMapping("/greeting")
    public String greeting(String greeting) {
        return "[" + new Date() + "]: " + greeting;
    }
}
