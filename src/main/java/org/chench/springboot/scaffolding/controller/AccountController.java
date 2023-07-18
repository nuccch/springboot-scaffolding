package org.chench.springboot.scaffolding.controller;

import org.chench.springboot.scaffolding.model.Account;
import org.chench.springboot.scaffolding.service.AccountService;
import org.chench.springboot.scaffolding.vo.JsonVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 简单示例
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.controller.AccountController
 * @date 2023.07.17
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping("/list")
    public Object list(HttpServletRequest req, HttpServletResponse resp,
                       @RequestParam("start") int start,
                       @RequestParam("offset") int offset) {
        //List<Account> accountList = accountService.getAccoutListByInt(start, offset);
        List<Account> accountList = accountService.getAccountListByXML(start, offset);
        if(logger.isDebugEnabled()) {
            logger.debug("account list: {}", accountList);
        }
        return JsonVO.build();
    }
}
