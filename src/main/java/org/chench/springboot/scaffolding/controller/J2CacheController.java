package org.chench.springboot.scaffolding.controller;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试验证j2cache的本地缓存功能
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.controller.J2CacheController
 * @date 2023.07.17
 */
@RestController
@RequestMapping("/j2cache")
public class J2CacheController {
    @Autowired
    private CacheChannel cacheChannel;
    String region = "default";

    @GetMapping("/set")
    public Object set(HttpServletRequest req, HttpServletResponse resp,
                      @RequestParam("key") String key,
                      @RequestParam("value") String value) {
        cacheChannel.set(region, key, value);
        return "success";
    }

    @GetMapping("/get")
    public Object get(HttpServletRequest req, HttpServletResponse resp,
                      @RequestParam("key") String key) {
        CacheObject cacheObject = cacheChannel.get(region, key);
        return cacheObject.getValue();
    }

}
