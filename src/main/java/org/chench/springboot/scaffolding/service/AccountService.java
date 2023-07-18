package org.chench.springboot.scaffolding.service;

import org.chench.springboot.scaffolding.mapper.AccountIntMapper;
import org.chench.springboot.scaffolding.mapper.AccountXmlMapper;
import org.chench.springboot.scaffolding.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务类
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.service.AccountService
 * @date 2023.07.17
 */
@Service
public class AccountService {
    @Autowired
    private AccountIntMapper accountIntMapper;

    @Autowired
    private AccountXmlMapper accountXmlMapper;

    /**
     * 使用接口映射器查询账户列表
     * @param start
     * @param offset
     * @return
     */
    public List<Account> getAccoutListByInt(int start, int offset) {
        return accountIntMapper.getAccoutList(start, offset);
    }

    /**
     * 使用xml映射器查询账户列表
     * @param start
     * @param offset
     * @return
     */
    public List<Account> getAccountListByXML(int start, int offset) {
        return accountXmlMapper.getAccountList(start, offset);
    }

}
