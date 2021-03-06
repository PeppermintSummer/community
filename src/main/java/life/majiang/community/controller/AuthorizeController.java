package life.majiang.community.controller;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;
import life.majiang.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
@Slf4j //日志
public class AuthorizeController {
    @Autowired     //写好的实例加载上下文
    private GithubProvider githubProvider;

    @Value("${github.client.id}")//value去配置文件中获取,对于不同的用户，可以去配置文件中获取用户信息
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;//mapper映射了就不用实现类了

    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           //HttpServletRequest request,//可以进行自动登录了，request就不需要了
                           HttpServletResponse response
                           ) throws IOException {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken= githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser=githubProvider.getUser(accessToken);
        //System.out.println(user.getName());//打印出用户信息
        if(githubUser!=null){
            User user= new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));//强制转换一下
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userService.createOrupdate(user);
            //userMapper.insert(user);//   存储到数据库中
            response.addCookie(new Cookie("token",token));
            //登录成功，写cookie和session
            //request.getSession().setAttribute("user",githubUser);
            return "redirect:/";//回到首页
        }else{
            log.error("callback get github ,{}",githubUser); //打印日志记录问题
            return "redirect:/";
            //登录失败、重新登录
        }
        //return "index";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){//退出登录需要删除cookie，session.cookie是通过response来设置的
        request.getSession().removeAttribute("user");
        Cookie cookie= new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";//返回主页
    }
}
