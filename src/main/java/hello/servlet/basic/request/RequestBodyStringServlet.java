package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); // getInputStream : message의 Body 내용을 Byte Code로 얻음.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); //Byte -> String 변환 spring의 util 사용.

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");
    }
}
