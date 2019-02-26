package com.sysu.mooc_backed.controller;

import com.sysu.mooc_backed.entity.User;
import com.sysu.mooc_backed.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/user/findList")
    public List<User> findList(){
        return userService.findList();
    }

    @RequestMapping("user/findUserByName")
    public User findListById(String name){
        return userService.findUserByName(name);
    }

    @RequestMapping("user/add")
    public void add(User user){
        userService.addUser(user);
    }

    @RequestMapping("user/update")
    public void update(User user){
        userService.updateUser(user);
    }

    @RequestMapping("user/delete")
    public String delete(int id){
        userService.deleteUser(id);
        return "delete successfully!";
    }

    @RequestMapping("user/login")
    public String doLogin(@RequestParam("name") String name, @RequestParam("password") String password){
        Subject currentUser = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        try{
            currentUser.login(token);
            if(currentUser.isAuthenticated()){
                System.out.println("登陆成功");
                return "success";
            }else{
                System.out.println("登陆失败");
                return "failure";
            }
        }catch (UnknownAccountException e){
            System.out.println("账户不存在");
            return "failure";
        }catch (IncorrectCredentialsException e){
            System.out.println("密码不正确");
            return "failure";
        }catch (AuthenticationException e){
            System.out.println("其他错误");
            return "failure";
        }
    }
}
