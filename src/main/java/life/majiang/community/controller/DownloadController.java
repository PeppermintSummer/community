package life.majiang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DownloadController {
    @GetMapping("/download")
    public String download(){
        return "download";
    }
}
