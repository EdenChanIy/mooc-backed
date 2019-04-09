package com.sysu.mooc_backed.controller;

import com.sysu.mooc_backed.common.utils.Result;
import com.sysu.mooc_backed.common.utils.StringUtils;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/user/findList")
    public Result findList(){
        return Result.success(userService.findList());
    }

    @RequestMapping("user/findUserByName")
    public Result findListById(String name){
        return Result.success(userService.findUserByName(name));
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

    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    public Result doLogin(@RequestParam("name") String name, @RequestParam("password") String password){
        Subject currentUser = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        if(StringUtils.isEmpty(name)){
            return Result.error("缺少name");
        }
        if(StringUtils.isEmpty(password)){
            return Result.error("缺少password");
        }

        try{
            currentUser.login(token);
            if(currentUser.isAuthenticated()){
                System.out.println("登陆成功");
                return Result.success("success");
            }else{
                System.out.println("登陆失败");
                return Result.error("登陆失败");
            }
        }catch (UnknownAccountException e){
            System.out.println("账户不存在");
            return Result.error("账户不存在");
        }catch (IncorrectCredentialsException e){
            System.out.println("密码不正确");
            return Result.error("密码不正确");
        }catch (AuthenticationException e){
            System.out.println("其他错误");
            return Result.error("其他错误");
        }
    }

//    @RequestMapping("/user/interest")
//    public Result findInterest(String userId){
//        try{
//            int userIdInt = Integer.parseInt(userId);
//            return Result.success(userService.findInterestListByUserId(userIdInt));
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.error("网络异常");
//        }
//    }
}
