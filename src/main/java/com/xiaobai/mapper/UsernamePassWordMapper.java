package com.xiaobai.mapper;

import com.xiaobai.entity.UsernamePassWord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UsernamePassWordMapper {
    Integer changepw(@Param("up") UsernamePassWord up, @Param("username") String username);
}
