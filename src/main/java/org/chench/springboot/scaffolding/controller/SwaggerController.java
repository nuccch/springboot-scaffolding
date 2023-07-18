package org.chench.springboot.scaffolding.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用Swagger实现API文档管理示例
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.controller.SwaggerController
 * @date 2023.07.17
 */
@Api(tags = "用户相关接口", description = "提供用户相关管理操作")
@RestController
@RequestMapping("/user")
public class SwaggerController {
    /**
     * 添加用户
     * @param req
     * @param resp
     * @param body
     * @return
     */
    @ApiOperation(value = "添加用户")
    @ApiImplicitParam(dataType = "application/json")
    @PostMapping("/add")
    public Object add(HttpServletRequest req, HttpServletResponse resp,
                      @RequestBody JSONObject body) {
        return null;
    }

    /**
     * 修改用户
     * @param req
     * @param resp
     * @param body
     * @return
     */
    @PostMapping("/update")
    public Object update(HttpServletRequest req, HttpServletResponse resp,
                         @RequestBody JSONObject body) {
        return null;
    }

    /**
     * 删除用户
     * @param req
     * @param resp
     * @param body
     * @return
     */
    @PostMapping("/delete")
    public Object delete(HttpServletRequest req, HttpServletResponse resp,
                         @RequestBody JSONObject body) {
        return null;
    }

    /**
     * 查询用户列表
     * @param req
     * @param resp
     * @param page
     * @param pageNum
     * @param name
     * @return
     */
    @GetMapping("/list")
    public Object list(HttpServletRequest req, HttpServletResponse resp,
                       @RequestParam("page") int page,
                       @RequestParam("pageNum") int pageNum,
                       @RequestParam("name") String name) {
        return null;
    }

    /**
     * 查询用户详情
     * @param req
     * @param resp
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public Object detail(HttpServletRequest req, HttpServletResponse resp,
                         @PathVariable("id") long id) {
        return null;
    }
}
