package life.majiang.community.controller;


import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Component
public class IndexController {
//
//    @Autowired
//    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")   //访问首页
    public String index(//HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "6") Integer size,
                        @RequestParam(name = "search" ,required = false) String search
                        ){

        // 搜索采用正则匹配的方式,也可以ElasticSearch,dubbo,springcloud,
        PaginationDTO pagination=questionService.list(search,page,size); //带有用户信息和question信息
        model.addAttribute("pagination",pagination);
        return "index";
    }
//    public String hello(@RequestParam(name="name") String name, Model model){
//        model.addAttribute("name" ,name);
//        return "index"; //自动去模板目录找
//    }
}
