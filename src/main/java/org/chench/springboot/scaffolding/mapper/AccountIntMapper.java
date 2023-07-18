package org.chench.springboot.scaffolding.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.chench.springboot.scaffolding.model.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MyBatis接口映射器，通过映射器注解定义SQL语句
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.mapper.AccountIntMapper
 * @date 2023.07.17
 */
@Repository
@Mapper
public interface AccountIntMapper {

    @Select("select id,name,email,ctime,mtime from account limit #{start}, #{offset}")
    public List<Account> getAccoutList(@Param("start") int start, @Param("offset") int offset);
}
