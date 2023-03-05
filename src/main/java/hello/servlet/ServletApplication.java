package hello.servlet;

import hello.servlet.web.springmvc.v1.SpringMemberFormControllerV1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@ServletComponentScan	//서블릿 자동 등록
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

	//requestMapping 사용 방법3. 직접 Bean등록하여  @Controller, @Component 안쓰고 사용.
//	@Bean
//	SpringMemberFormControllerV1 springMemberFormControllerV1(){
//		return new SpringMemberFormControllerV1();
//	}

}
