package com.xiao.account.mapper;


import com.xiao.account.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {

    Account selectByUserId(@Param("userId") String userId);

    int updateById(@Param("account") Account record);

}
