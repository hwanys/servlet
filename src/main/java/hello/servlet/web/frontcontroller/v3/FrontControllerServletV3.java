package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();    //요청 URI 가져와서 저장

        ControllerV3 controller = controllerMap.get(requestURI);    // 요청 주소에 해당하는 Controller 저장.
        if (controller == null) {   // 해당 URI와 일치하는 controller가 없을 시.
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 status의 response return
            return;
        }

        //paramMap -> key(paramName) : value(request.getParameter(paramName) 들어있음.

        Map<String, String> paramMap = createParamMap(request); //메서드 뽑기 -> "ctrl + Alt + M"
        ModelView mv = controller.process(paramMap);// 호출 시 ControllerV3 interface의 process메소드 호출

        //new-form
        String viewName = mv.getViewName();//논리이름 new-form
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request,response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
