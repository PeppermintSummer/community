package life.majiang.community.controller;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}") //请求映射地址
    public  String question(@PathVariable(name = "id") Long id,
                            Model model){  //拿到参数

        QuestionDTO questionDTO=questionService.getById(id);//传到页面上去，需要Model
        //累加阅读数
        questionService.inView(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
