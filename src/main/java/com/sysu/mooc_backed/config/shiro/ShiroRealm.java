package com.sysu.mooc_backed.config.shiro;

import com.sysu.mooc_backed.entity.User;
import com.sysu.mooc_backed.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShiroRealm extends AuthenticatingRealm {
    @Autowired
    private UserService userService;

    private SimpleAuthenticationInfo info = null;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)throws AuthenticationException{
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String userName = upToken.getUsername();
        User userInfo = userService.findUserByName(userName);

        if(null!=userInfo){
            Object principal = userInfo.getName();
            Object credentials = userInfo.getPassword();

            String realmName = this.getName();

            info = new SimpleAuthenticationInfo(principal, credentials, realmName);
        }else{
            throw new AuthenticationException();
        }

        return info;
    }

}
