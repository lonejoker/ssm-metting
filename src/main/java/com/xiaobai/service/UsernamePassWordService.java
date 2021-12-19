package com.xiaobai.service;

import com.xiaobai.mapper.UsernamePassWordMapper;
import com.xiaobai.entity.UsernamePassWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:36
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName UsernamePassWordService
 */
@Service
public class UsernamePassWordService {

    @Autowired
    private UsernamePassWordMapper usernamePassWordMapper;

    public Integer changepw(UsernamePassWord up, String username) {
        String newpassword = up.getNewpassword();
        if (newpassword.equals(up.getConfirmpassword())) {
            return usernamePassWordMapper.changepw(up, username);
        } else {
            return -1;
        }
    }
}
