package life.majiang.community.controller;


import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Component
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")   //访问首页
    public String index(HttpServletRequest request,
                        Model model){
        Cookie[] cookies = request.getCookies();//request可以去设置cookie
        if (cookies!=null && cookies.length!=0)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        List<QuestionDTO> questionList=questionService.list();//带有用户信息和question信息
        model.addAttribute("questions",questionList);
        return "index";
    }
//    public String hello(@RequestParam(name="name") String name, Model model){
//        model.addAttribute("name" ,name);
//        return "index";//自动去模板目录找
//    }
}
