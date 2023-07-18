package org.chench.springboot.scaffolding.mapper;

import org.chench.springboot.scaffolding.model.Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 测试DAO层接口，跟测试服务组件一样，不需要web环境，但是需要提供Spring容器环境。
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.mapper.AccountMapperTest
 * @date 2023.07.17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AccountMapperTest {
    @Autowired
    private AccountIntMapper accountIntMapper;

    @Autowired
    private AccountXmlMapper accountXmlMapper;

    @Test
    public void testAccountIntMapper() {
        List<Account> accountList = accountIntMapper.getAccoutList(0, 20);
        Assert.assertTrue(accountList != null);
    }

    @Test
    public void testAccountXmlMapper() {
        List<Account> accountList = accountXmlMapper.getAccountList(0, 20);
        Assert.assertTrue(accountList != null);
    }

}
