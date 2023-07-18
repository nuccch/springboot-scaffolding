package org.chench.springboot.scaffolding.service;

import org.chench.springboot.scaffolding.model.Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 测试Service组件，不需要web环境，但是需要提供Spring容器环境。
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.service.AccountServiceTest
 * @date 2023.07.17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void testGetAccountListByInt() {
        int start = 0;
        int offset = 10;
        List<Account> accountList = accountService.getAccoutListByInt(start, offset);
        Assert.assertTrue(accountList != null);
    }
}
