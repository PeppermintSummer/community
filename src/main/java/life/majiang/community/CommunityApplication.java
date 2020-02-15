package life.majiang.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@SpringBootApplication
public class CommunityApplication {
//	@RequestMapping("/")
//	public String first(){
//		return "My first Springboot!";
//	}

	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}
}
