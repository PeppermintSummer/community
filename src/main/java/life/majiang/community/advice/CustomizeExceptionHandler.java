package life.majiang.community.advice;

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    //@ResponseBody
    ModelAndView handle(Throwable e,
                        Model model,
                        HttpServletRequest request,
                        HttpServletResponse response){
        //HttpStatus status=getStatus(request);
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)){
            ResultDTO resultDTO;
            //返回JSON
            if (e instanceof CustomizeException){
                resultDTO= ResultDTO.errorOf((CustomizeException) e);
            }else {
                resultDTO= ResultDTO.errorOf(CustomizeErrorCode.SYSTEM_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close(); //关闭流
            } catch (IOException ioe) {

            }
            return null;

        }else {
            //错误页面跳转
            if (e instanceof CustomizeException){
                model.addAttribute("message",e.getMessage());
            }else {
                model.addAttribute("message", CustomizeErrorCode.SYSTEM_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
