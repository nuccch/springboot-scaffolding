package org.chench.springboot.scaffolding.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.chench.springboot.scaffolding.model.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MyBatis XML映射器，在xml文件中定义SQL语句
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.mapper.AccountXmlMapper
 * @date 2023.07.17
 */
@Repository
@Mapper
public interface AccountXmlMapper {
    public List<Account> getAccountList(int start, int offset);
}
