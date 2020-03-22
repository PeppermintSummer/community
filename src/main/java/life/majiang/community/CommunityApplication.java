package life.majiang.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@RestController
@SpringBootApplication
@MapperScan(basePackages = "life.majiang.community.mapper")
@EnableScheduling
public class CommunityApplication {
//	@RequestMapping("/")
//	public String first(){
//		return "My first Springboot!";
//	}

	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}
}
