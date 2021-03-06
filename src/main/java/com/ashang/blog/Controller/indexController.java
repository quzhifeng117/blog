package com.ashang.blog.Controller;

import com.ashang.blog.Dao.UserDao;
import com.ashang.blog.Entity.Constant.Status;
import com.ashang.blog.Entity.Response.Resp;
import com.ashang.blog.Entity.User;
import com.ashang.blog.Entity.Utils.RespUtil;
import com.ashang.blog.Service.impl.UserServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/blog")
//@CrossOrigin(origins = "http://localhost:8001",maxAge = 3600)
public class indexController {

    @Autowired
    private UserDao userDao;

    @Autowired
    UserServiceImpl userService;

    @GetMapping(path = "/index")
    @ResponseBody
    public String index(){
        return "hao!";
    }

    /**
     * 注册接口
     * @param user
     * @return
     */
    @ApiOperation(value = "注册用户",notes = "")
    @ApiImplicitParam(name = "user",value = "包含用户名和密码的user类")
    @GetMapping(path = "/register/user")
    public Resp register(User user){
        if(user.getUsername()==null||user.getPassword()==null){
            return RespUtil.errorResp(Status.Api.ERROR.getCode(),"用户名或密码不能为空！");
        }
        userService.register(user);
        return RespUtil.successResp();
    }

    /**
     * 登录接口
     * @param user 登录用户
     * @param request  第一次登录传入Session用于后续使用
     * @return
     */
    @ApiOperation(value = "用户登录")
    @ApiImplicitParam(name ="user", value = "user类")
    @GetMapping(path = "/login")
    public Resp login(User user , HttpServletRequest request){
        if(userService.login(user).getCode()==2000){
            HttpSession httpSession=request.getSession(true);
            httpSession.setAttribute("user",user);
            return RespUtil.successResp();
        }
        return RespUtil.errorResp(Status.Api.ERROR.getCode(),Status.Api.ERROR.getMsg());
    }


}
